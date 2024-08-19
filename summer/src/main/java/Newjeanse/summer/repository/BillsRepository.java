package Newjeanse.summer.repository;

import Newjeanse.summer.domain.Bills;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BillsRepository extends MongoRepository<Bills, String> {
    @Query("{ 'userId': ?0 }")
    List<Bills> findByUserId(String userId);

    @Query("{ 'userId': ?0, 'name': ?1 }")
    Bills changeDate(String userId, String name);

}
