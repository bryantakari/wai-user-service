package com.app.wai.user_service.repository;

import com.app.wai.user_service.model.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is jpa repository of user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<Account,Integer> {
    // Find by username (unique)
    Optional<Account> findByUsername(String username);
}
