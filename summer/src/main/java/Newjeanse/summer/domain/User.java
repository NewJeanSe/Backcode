package Newjeanse.summer.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;       // 아이디
    private String nickname;     // 닉네임
    private String email;
    private Role role;
    private String password;    // 실사용 비밀번호
}
