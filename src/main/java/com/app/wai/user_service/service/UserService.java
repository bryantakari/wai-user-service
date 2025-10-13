package com.app.wai.user_service.service;

import com.app.wai.user_service.model.entity.Account;
import java.util.Optional;

/**
 * User Service Interface.
 */
public interface UserService {

    Account insertAccount(Account account);

    Optional<Account> getAccountByUsername(String username);
}
