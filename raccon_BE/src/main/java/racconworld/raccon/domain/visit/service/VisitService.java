package racconworld.raccon.domain.visit.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.visit.repository.VisitRepository;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final TestRepository testRepository;


    public void getTotalVisited() {

        Long totalTestView = testRepository.findAllByView();



    }




}
