package practice.ex.book_shop.service;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practice.ex.book_shop.configuration.JwtService;
import practice.ex.book_shop.dto.authentication.AuthenticationRequest;
import practice.ex.book_shop.dto.authentication.AuthenticationResponse;
import practice.ex.book_shop.dto.register.RegisterRequest;
import practice.ex.book_shop.entities.User;
import practice.ex.book_shop.repositories.UserRepository;
import practice.ex.book_shop.user.Role;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtService _jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(_passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        _userRepository.save(user);
        var jwtToken = _jwtService.generatedToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private final AuthenticationManager _authenticationManager;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = _userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = _jwtService.generatedToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User getUsernameFromToken(String token){

        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return _userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null"));
    }
}
