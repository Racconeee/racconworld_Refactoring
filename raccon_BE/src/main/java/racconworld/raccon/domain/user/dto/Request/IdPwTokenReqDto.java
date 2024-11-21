package racconworld.raccon.domain.user.dto.Request;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class IdPwTokenReqDto {

    private String username;
    private String password;



}
