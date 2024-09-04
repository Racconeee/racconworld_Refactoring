package racconworld.raccon.domain.log.service;


import org.springframework.stereotype.Service;
import racconworld.raccon.domain.log.entity.Log;

public interface LogService {

    void saveLog(Log logEntity);
}
