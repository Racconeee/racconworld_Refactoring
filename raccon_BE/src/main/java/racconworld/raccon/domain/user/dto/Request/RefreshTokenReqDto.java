package racconworld.raccon.domain.user.dto.Request;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class RefreshTokenReqDto {

    private String RefreshToken;
}
