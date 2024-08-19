package Newjeanse.summer.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
public class UserForm {
    @NotBlank(message = "회원 아이디를 입력해주세요")
    private String username;       // 아이디

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;     // 닉네임

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 6, message = "비밀번호는 최소 6자리 입니다")
    private String password1;    // 실사용 비밀번호

    @NotBlank(message = "비밀번호를 다시 한번 입력해주세요")
    private String password2;    // 확인용 비밀번호

    @NotBlank(message = "이메일 인증 코드를 입력해주세요")
    private String emailcode;

    // 이메일 인증확인 (default = false)
    private boolean auth = false;

    public boolean PasswordMatching(){
        return this.password1.equals(this.password2);
    }



}
