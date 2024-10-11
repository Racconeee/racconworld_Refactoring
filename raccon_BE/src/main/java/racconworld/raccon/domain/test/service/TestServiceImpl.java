package racconworld.raccon.domain.test.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;
import racconworld.raccon.domain.upload.dto.Response.TestTotalVisitResDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
//    @Cacheable(cacheNames = "getTestList", key = "'pageNumber:' + #p0", condition = "#page == 0", cacheManager = "cacheManager")
    @Cacheable(cacheNames = "getTestList", key = "'pageNumber:' + #p0", condition = "#page == 0", cacheManager = "cacheManager")
    public ShowTestResDto getTestListByPage(int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC, "view"));
        Slice<Test> page = testRepository.findAll(pageRequest);
        List<ShowTestResDto.ShowTestListDto> showTestListDtos = page.map(t -> new ShowTestResDto.ShowTestListDto(t.getId() , t.getTestName() ,t.getView(), t.getFileName() , t.getFilePath())).stream().toList();

        return new ShowTestResDto(page.hasNext(), showTestListDtos);
    }


    @Override
    @Cacheable(cacheNames = "getTestTotalVisit", key = "'TestTotalVisit'", cacheManager = "cacheManager")
    public TestTotalVisitResDto getTestTotalVisit() {
        return new TestTotalVisitResDto(testRepository.findAllByView());
    }
}
