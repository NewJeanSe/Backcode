package Newjeanse.summer.service;


import Newjeanse.summer.domain.Role;
import Newjeanse.summer.domain.User;
import Newjeanse.summer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public User nicknameExist(String nickname){
        return userRepository.findByNickname(nickname);
    }

    public User UsernameExist(String username){
        return userRepository.findByUsername(username);
    }

    public User EmailExist(String email){
        return userRepository.findByEmail(email);
    }

    public User ExistingUser(String username, String password){
        return userRepository.findByExistingUser(username, password);
    }


}
