package Newjeanse.summer.repository;

import Newjeanse.summer.domain.Districts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DistrictsRepository extends MongoRepository<Districts, String> {
    @Query("{ 'userId': ?0 }")
    List<Districts> findByUserId(String userId);

    @Query("{ 'userId': ?0, 'name': ?1 }")
    Districts findData(String userId, String name);

}
