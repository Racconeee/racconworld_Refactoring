package racconworld.raccon.domain.test.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import racconworld.raccon.domain.test.dto.Response.showTestResDto;
import racconworld.raccon.domain.test.entity.Test;
import racconworld.raccon.domain.test.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Transactional

    public showTestResDto getTestListByPage(int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, 9, Sort.by(Sort.Direction.ASC, "id"));
        Slice<Test> page = testRepository.findAllOrderByViewAsc(pageRequest);
        List<showTestResDto.showTestListDto> showTestListDtos = page.map( t -> new showTestResDto.showTestListDto(t.getId() , t.getTestName() ,t.getView(), t.getFileName() , t.getFilePath())).stream().toList();

        return new showTestResDto(page.hasNext(), showTestListDtos);
    }


}
