//package racconworld.raccon.global.jwt.service;
//
//import io.jsonwebtoken.Claims;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class JwtClaimsParser {
//
//    public static Collection<GrantedAuthority> getROLE(Claims claims) {
//    Object stringAuthorities = claims.get("ROLE");
//    Collection<GrantedAuthority> authorities = new ArrayList<>();
//    if (stringAuthorities instanceof Collection<?>) {
//        for (Object grantedAuthority : (Collection<?>) stringAuthorities) {
//            if (grantedAuthority instanceof String) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + grantedAuthority));
//            }
//        }
//    }
//    return authorities;
//    }
//
//}