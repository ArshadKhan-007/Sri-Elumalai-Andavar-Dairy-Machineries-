package dev.misfit.backend.repository;

import dev.misfit.backend.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Integer> {
    UserEntity findByUserEmail(String userEmail);
}
