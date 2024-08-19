package Newjeanse.summer.controller;

import Newjeanse.summer.configuration.SessionConst;
import Newjeanse.summer.domain.User;
import Newjeanse.summer.service.MailService;
import Newjeanse.summer.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;


    // 로그인
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request){
        System.out.println(user.getUsername() + " " + user.getPassword());
        User existingUser = userService.ExistingUser(user.getUsername(), user.getPassword());

        Map<String, String> response = new HashMap<>();

        if(existingUser != null){
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.LOGIN_USER, existingUser);

            response.put("status", "success");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }

    // 로그아웃
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("success");
    }

    // 로그인 체크
    @PostMapping("checkLogin")
    public ResponseEntity<?> checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession(false);


        if (session != null && session.getAttribute(SessionConst.LOGIN_USER) != null) {
            User loginUser = (User) session.getAttribute(SessionConst.LOGIN_USER);

            Map<String, String> response = new HashMap<>();
            response.put("username", loginUser.getUsername());
            response.put("userId", loginUser.getNickname());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
    }

//    // 아이디 찾기
//    @PostMapping("findId")
//    public ResponseEntity<?> findId(@RequestBody Map<String, String> request){
//        String email = request.get("email");
//
//    }

    // 비밀번호 찾기

}
