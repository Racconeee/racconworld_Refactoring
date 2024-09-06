package racconworld.raccon.global.jwt.dto.Request;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class IdPwTokenReqDto {

    private String username;
    private String password;



}
