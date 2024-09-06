package racconworld.raccon.domain.log.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.log.entity.Log;
import racconworld.raccon.domain.log.repository.LogRepository;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService  {

    private final LogRepository logRepository;


    @Override
    public void saveLog(Log logEntity) {
        logRepository.save(logEntity);
    }
}
