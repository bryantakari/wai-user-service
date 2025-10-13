package com.app.wai.user_service.service.impl;

import com.app.wai.user_service.model.entity.Account;
import com.app.wai.user_service.repository.UserRepository;
import com.app.wai.user_service.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Account insertAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return repository.findByUsername(username);
    }
}
