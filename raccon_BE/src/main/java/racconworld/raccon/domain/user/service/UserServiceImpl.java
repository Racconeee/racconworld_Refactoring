package racconworld.raccon.domain.user.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.user.entity.Role;
import racconworld.raccon.domain.user.entity.User;
import racconworld.raccon.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public String signUpAdmin(String username, String password) {

        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

}
