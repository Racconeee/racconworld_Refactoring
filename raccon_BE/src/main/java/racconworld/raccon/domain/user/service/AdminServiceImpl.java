package racconworld.raccon.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.repository.TestRepository;


/*
*
* deleteTest에서 방법이 2가지 있다고 생각이 든다.
* 현재 삭제를 하면 쿼리 가 3번 나감
* 연관관계 매핑으로 되어 있는 애들을 삭제하기 위해서는 test -> 조회 후 Question,choice 삭제
* Question를 불러오기위해 쿼리를 날림 choice는 알아서 삭제해서 쿼리 안날림
* 결국 test 조회 , Question 조회 ,test 삭제 3번의 쿼리가 나간다.
* 나중에 쿼리문으로 해결 가능
* 1.findById를 해서 먼저 찾아서 있는지 없는지 예외를 먼저 터트리기
* 2.먼저 삭제하고 반환값이 0이면 없는 데이터니까 그떄 예외 터트리기
* 뭐가 좋을까
* 1번 방법은 무조건 쿼리를 1방 날린다.
* 2번 방법은 처음 부터 쿼리를 3번 날리는줄 알았지만 값이 없으면 1번 날리고 멈추게 된다.
* 그러면 무조건 2번 방법이 좋을거같다.
*
* 1번 방법
*
        testRepository.findById(testId).orElseThrow( () -> new CustomExceptionHandler(ErrorCode.BAD_REQUEST , "해당하는 TestID가 존재하지않습니다."));
        testRepository.deleteById(testId);
*
* 2번 방법
*
        int deletedCount = testRepository.deleteTestById(testId);
        if (deletedCount == 0) {
            throw new CustomExceptionHandler(ErrorCode.BAD_REQUEST, "해당하는 TestID가 존재하지 않습니다.");
        }
*
* 하지만 또 생각해보니 유저가 아닌 관리자 페이지에서 하는데
* 이런 조회를 하는건 쓸데 없이 하는 테스트 같은 느낌
* 차라리 바로 삭제하자
*
* */

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements  AdminService{

    private final TestRepository testRepository;



    public String deleteTest(Long testId) {
        testRepository.deleteById(testId);
        return "Test 삭제 성공!";
    }
}
