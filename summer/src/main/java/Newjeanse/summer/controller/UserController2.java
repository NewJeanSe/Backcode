//package Newjeanse.summer.controller;
//
//import Newjeanse.summer.configuration.SessionConst;
//import Newjeanse.summer.domain.User;
//import Newjeanse.summer.repository.UserRepository;
//import Newjeanse.summer.service.MailService;
//import Newjeanse.summer.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class UserController2 {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private MailService mailService;
//
//    protected UserForm userForm;
//    private int number;
//
//    @GetMapping("register")
//    public String register(Model model) {
//        model.addAttribute("userForm", new UserForm());
//        return "/userlogin/newuser/register";
//    }
//
//    @GetMapping("/userlogin/naver")
//    public String naver() {
//        return "/userlogin/naver";
//    }
//
//    @PostMapping("register")
//    public String register(@RequestBody UserForm userForm, BindingResult result){
//        this.userForm = userForm;
//        User user = new User();
//        user.setUserId(userForm.getUserId());
//        user.setUsername(userForm.getUsername());
//        user.setEmail(userForm.getEmail());
//        user.setPassword1(userForm.getPassword1());
//
//        System.out.println("userId : " + user.getUserId()
//                            + " username : " + user.getUsername()
//                            + " email : " + user.getEmail()
//                            + " password1 : " + user.getPassword1());
//        // 패스워드 확인 메소드
//        if (!userForm.PasswordMatching()) {
//            result.addError(new FieldError("userForm", "password2", "비밀번호가 일치하지 않습니다."));
//        }
//
//        // 오류가 발생하면 다시 회원가입 페이지로 리턴
//        if (result.hasErrors() || !userForm.isAuth()) {
//            return "/userlogin/newuser/register";
//        }
//
//        // 정상적으로 등록
//        userService.save(user);
//        return "redirect:/";
//    }
//
//    // 인증 이메일 전송
//    @PostMapping("/userlogin/newuser/sendMail")
//    public void mailSend(@RequestBody String email) {
//        number = mailService.sendMail(email);
//    }
//
//    // 인증번호 일치여부 확인
//    @PostMapping("/userlogin/newuser/checkMail")
//    public ResponseEntity<String> mailCheck(@RequestBody String emailcode) {
//        emailcode = emailcode.replace("\"", "");
//
//        int intEmailcode = Integer.parseInt(emailcode);
//
//        if (intEmailcode == number) {
//            userForm.setAuth(true);
//            return ResponseEntity.ok("Valid code");
//        } else {
//            // 유효하지 않은 경우, 400 BAD REQUEST 상태 코드와 메시지 반환
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
//        }
//    }
//
//    // 로그인 페이지
//    @GetMapping("login")
//    public String login(Model model){
//        model.addAttribute("userForm", new UserForm());
//        return "/userlogin/login";
//    }
//
//    // 로그인시 등록되어있는 사용자인지 확인
//    @PostMapping("login")
//    public String loginUser(User user, HttpServletRequest request) {
//        User existingUser = userRepository.findByUserloginId(user.getUserId());
//        if (existingUser != null && existingUser.getPassword1().equals(user.getPassword1())) {
//            HttpSession session = request.getSession();
//            session.setAttribute(SessionConst.LOGIN_USER, existingUser);
//            return "redirect:/userlogin/users";
//        } else {
//            return "/userlogin/login";
//        }
//    }
//
//    @GetMapping("/userlogin/users")
//    public String userslogin(Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "redirect:/userlogin/login";
//        }
//
//        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
//
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "/userlogin/users";
//        } else {
//            return "redirect:/userlogin/login";
//        }
//    }
//
//    @PostMapping("/userlogin/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/userlogin/userdata/userInfo")
//    public String userInfo(Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "redirect:/userlogin/login";
//        }
//
//        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "/userlogin/userdata/userInfo";
//        } else {
//            return "redirect:/userlogin/users";
//        }
//    }
//
//    @GetMapping("/userlogin/changeInfo/changeUsername")
//    public String changeUsername(){
//        return "/userlogin/changeInfo/changeUsername";
//    }
//
//    @PostMapping("/userlogin/changeInfo/changeUsername")
//    public String changeUsername(@RequestParam("newUsername") String newUsername, Model model, HttpServletRequest request ){
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "redirect:/userlogin/login";
//        }
//
//        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
//        userRepository.delete(user);
//
//        user.setUsername(newUsername);
//        userRepository.save(user);
//        return "redirect:/userlogin/userdata/userInfo";
//    }
//
//    @GetMapping("/userlogin/changeInfo/changePassword")
//    public String changePassword() {
//        return "/userlogin/changeInfo/changePassword";
//    }
//
//    @PostMapping("/userlogin/changeInfo/changePassword")
//    public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, @RequestParam("checkPassword") String checkPassword, Model model, HttpServletRequest request){
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "redirect:/userlogin/login";
//        }
//
//        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
//        if(user != null && user.getPassword1().equals(currentPassword)){
//            if(newPassword.equals(checkPassword)){
//                userRepository.delete(user);
//                user.setPassword1(newPassword);
//                userRepository.save(user);
//                return "redirect:/userlogin/userdata/userInfo";
//            }
//            else {
//                model.addAttribute("error", "새로운 비밀번호가 일치하지 않습니다.");
//            }
//        }
//        else{
//            model.addAttribute("error", "현재 비밀번호가 틀립니다.");
//        }
//        return "/userlogin/changeInfo/changePassword";
//    }
//}
