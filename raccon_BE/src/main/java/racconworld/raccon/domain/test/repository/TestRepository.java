package racconworld.raccon.domain.test.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import racconworld.raccon.domain.test.entity.Test;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {



    @Query("SELECT t FROM Test t ORDER BY t.view DESC")
    Slice<Test> findAllOrderByViewDesc(Pageable pageable);

    @Query("SELECT t FROM Test t WHERE t.testName = :testName")
    Optional<Test> findByTestName(String testName);

    @Modifying
    @Query("update Test t set t.view = t.view + 1 where t.id = :testId")
    void updateTestByView(@Param("testId") Long testId);



    //
//
//    @Query("SELECT t FROM Test t JOIN FETCH t.questions WHERE t.id = :id")
////    @Query("SELECT t FROM Test t JOIN FETCH t.questions q JOIN FETCH q.choices WHERE t.id = :id")
//    Test findByIdWithQuestions(@Param("id") Long id);
//
//    @Query("SELECT t FROM Test t WHERE t.filepath = :filepath")
//    Optional<Test> findByFilepath(@Param("filepath") String filepath);
//
//    @Query("SELECT t FROM Test t JOIN FETCH t.results WHERE t.id = :test_id")
//    Optional<Test> findByIdAAndResults(Long test_id);
//
//    @Modifying
//    @Query("update Test t set t.views = t.views + 1 where t.id = :id")
//    void updateTestByViews(@Param("id") Long id);
//
//    Optional<Test> findByTestName(String testName);
//
//    void deleteById(Long id);
//    List<Test> findTop5ByOrderByViewsDesc();

}
