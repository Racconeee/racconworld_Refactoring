package racconworld.raccon.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Builder
    public User(String username, String password, String refreshToken, Role roles) {
        this.username = username;
        this.password = password;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }


}
