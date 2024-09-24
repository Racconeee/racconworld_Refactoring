package racconworld.raccon.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> role;

    @Builder
    public User(String username, String password, String refreshToken, Role role) {
        this.username = username;
        this.password = password;
        this.refreshToken = refreshToken;
        this.role = Collections.singletonList(role);
    }


    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}
