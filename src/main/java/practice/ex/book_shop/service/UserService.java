package practice.ex.book_shop.service;

import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import practice.ex.book_shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import practice.ex.book_shop.dto.user.UserRequest;
import practice.ex.book_shop.dto.user.UserResponse;
import practice.ex.book_shop.entities.User;
import practice.ex.book_shop.mapper.UserMapper;
import practice.ex.book_shop.repositories.UserRepository;
import practice.ex.book_shop.service.interfaces.UserServiceInterface;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//1
    @Override
    public UserResponse getById(Integer id, String token) {
        User actionUser = getUsernameFromToken(token);
        System.out.println(actionUser.getEmail());

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        return userMapper.toDto(user.get());
    }
//2
    @Override
    public User getUsernameFromToken(String token) {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null"));

    }
//3
    @Override
    public void deleteById(Integer id) {
        if (userRepository.findById((id)).isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        userRepository.deleteById((id));
    }

    @Override
    public void updateById(Integer id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        userRepository.save(user.get());
    }

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toDto(userRepository.findAll());
    }
}
