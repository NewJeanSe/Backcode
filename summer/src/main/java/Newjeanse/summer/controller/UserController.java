package Newjeanse.summer.controller;

import Newjeanse.summer.domain.User;
import Newjeanse.summer.service.MailService;
import Newjeanse.summer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    protected UserForm userForm;

    private int number;

    @GetMapping("register")
    public ResponseEntity<?> register() {
        return ResponseEntity.ok("access successful");
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserForm userForm){
        this.userForm = userForm;
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setNickname(userForm.getNickname());
        user.setPassword(userForm.getPassword1());

        try {
            userService.save(user); 
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }

    // 아이디 중복 확인
    @PostMapping("existUsername")
    public ResponseEntity<?> usernameExist(@RequestBody Map<String, String> request){
        String username = request.get("username");
        if(userService.UsernameExist(username) == null){
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("fail");
        }
    }

    // 이메일 중복 확인
    @PostMapping("existEmail")
    public ResponseEntity<?> emailExist(@RequestBody Map<String, String> request){
        String email = request.get("email");
        return ResponseEntity.ok(userService.EmailExist(email));
    }

    // 닉네임 중복 확인
    @PostMapping("existNickname")
    public ResponseEntity<?> nicknameExist(@RequestBody Map<String, String> request){
        String nickname = request.get("nickname");
        return ResponseEntity.ok(userService.nicknameExist(nickname));
    }

    // 인증 이메일 전송
    @PostMapping("sendMail")
    public ResponseEntity<?> mailSend(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println(email);

        try{
            number = mailService.SendCodeMail(email);
            return ResponseEntity.ok(number);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }
    
    // sendMail에서 번호를 넘겨줘서 따로 코드를 만들지 않아도 됨
//    // 인증번호 일치여부 확인
//    @PostMapping("checkMail")
//    public ResponseEntity<?> mailCheck(@RequestBody Map<String, String> request) {
//        String emailcode = request.get("emailcode").replace("\"", "");
//
//        int intEmailcode = Integer.parseInt(emailcode);
//        System.out.println("intEmailcode : " + intEmailcode + " number : " + number);
//
//        if (intEmailcode == number) {
//            return ResponseEntity.ok("success");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
//        }
//    }
}

