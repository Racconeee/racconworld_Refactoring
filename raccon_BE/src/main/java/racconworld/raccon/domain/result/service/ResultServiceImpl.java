package racconworld.raccon.domain.result.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.result.repository.ResultRepository;
import racconworld.raccon.domain.test.repository.TestRepository;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {


    private final ResultRepository repository;
    private final TestRepository testRepository;

    @Override
    public String showResult(Long score, Long testId) {
        testRepository.findById(testId);
        return "Rsult 조회 생공 ";
    }
}
