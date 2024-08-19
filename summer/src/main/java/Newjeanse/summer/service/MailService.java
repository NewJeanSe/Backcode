package Newjeanse.summer.service;


import Newjeanse.summer.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "tlsdmlwls21@gmail.com";
    private static int number;

    @Autowired
    private UserRepository userRepository;

    // 랜덤으로 숫자 생성
    public static void createNumber() {
        number = (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    // 이메일 코드 메일 생성
    public MimeMessage CreateCodeMail(String email) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("Failed to create email message", e);
            throw new RuntimeException("Failed to create email message", e);
        }

        return message;
    }

    // 이메일 코드 메일 전송
    public int SendCodeMail(String email) {
        MimeMessage message = CreateCodeMail(email);
        javaMailSender.send(message);

        return number;
    }

//    // 아이디 찾기 메일 생성
//    public MimeMessage CreateFindIdMail(String email) {
//        User user = userRepository.findByEmail(email);
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            message.setFrom(senderEmail);
//            message.setRecipients(MimeMessage.RecipientType.TO, email);
//            message.setSubject("NewJeanse 아이디 찾기");
//            String body = "";
//            body += "<h3>" + "요청하신 아이디입니다." + "</h3>";
//            body += "<h1>" + user.getUserId() + "</h1>";
//            body += "<h3>" + "감사합니다." + "</h3>";
//            message.setText(body,"UTF-8", "html");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            log.error("Failed to create email message", e);
//            throw new RuntimeException("Failed to create email message", e);
//        }
//
//        return message;
//    }
//
//    // 아이디 찾기 메일 전송
//    public void SendFindIdMail(String email){
//        MimeMessage message = CreateFindIdMail(email);
//        javaMailSender.send(message);
//    }




}
