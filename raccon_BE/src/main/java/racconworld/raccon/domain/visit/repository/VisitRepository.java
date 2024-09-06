package racconworld.raccon.domain.visit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import racconworld.raccon.domain.visit.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit , String> {

    @Modifying
    @Query("UPDATE Visit v SET v.visitCount = v.visitCount + 1 WHERE v.id = 'total_count'")
    void incrementVisitCount();


}
