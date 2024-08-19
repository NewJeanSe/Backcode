package Newjeanse.summer.repository;

import Newjeanse.summer.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'nickname': ?0 }")
    User findByNickname(String nickname);

    @Query("{ 'username': ?0 }")
    User findByUsername(String username);

    @Query("{ 'email': ?0 }")
    User findByEmail(String email);

    @Query("{ 'username': ?0, 'password': ?1 }")
    User findByExistingUser(String username, String password);

}
