package racconworld.raccon.global.Security.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.user.entity.User;
import racconworld.raccon.domain.user.repository.UserRepository;
import racconworld.raccon.global.common.code.ErrorCode;
import racconworld.raccon.global.exception.CustomExceptionHandler;

import java.util.Collection;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userRepository.findByUsername(username).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.BAD_REQUEST,"해당 User가 없습니다."));

        return CustomUserDetails.builder()
                .username(user.getUsername())
                .authorities(getAuthorities(user))
                .build();
    }

    //name() 메서드는 Java의 Enum 클래스에서 제공하는 메서드로, 열거형(enum) 상수의 이름을 문자열로 반환합니다.
    protected Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList(user.getRole().stream().map(Enum::name).toList());
    }
}
