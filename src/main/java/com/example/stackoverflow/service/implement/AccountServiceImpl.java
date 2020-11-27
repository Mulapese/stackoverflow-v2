package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.exception.exceptionType.AccountNotFoundException;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.exception.exceptionType.WrongHeaderInfoException;
import com.example.stackoverflow.jwt.JwtTokenUtil;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.repository.AccountRepository;
import com.example.stackoverflow.service.serviceInterface.AccountService;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements CRUDService<Account, Account>, AccountService, UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Not validate yet
    @Override
    public int insert(String token, Account account) {
        String id = String.valueOf(account.getAccountId());

        if (findById(id).isPresent()) {
            return 0;
        }

        if (account.getCreatedTime() == null) {
            account.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        }

        repository.save(account);
        return 1;
    }

    @Override
    public List<Account> findAll() {
        return repository.findByOrderByAccountIdDesc();
    }

    @Override
    public Optional<Account> findById(String id) {
        return repository.findById(Integer.parseInt(id));
    }

    @Override
    public int update(String id, Account account) {

        if (!findById(id).isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("accountId"));
        }
        if (!id.equals(account.getAccountId() + "")) {
            throw new WrongHeaderInfoException(ErrorMessage.notMatch("accountId"));
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        for (ConstraintViolation<Account> violation : violations) {
            System.out.println(violation.getMessage());
        }
        account.setAccountId(Integer.parseInt(id));
        repository.save(account);
        return 1;
    }

    @Override
    public Optional<Account> delete(String id) {
        int idInt = Integer.parseInt(id);
        Optional<Account> account = repository.findById(idInt);
        repository.deleteById(idInt);
        return account;
    }

    @Override
    public Account findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Account getAccountFromToken(String token) {
        // Delete first 7 character "bearer "
        String email = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        Account account = findByEmail(email);
        if (account == null) {
            throw new AccountNotFoundException("The email or password is wrong.");
        }
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = findByEmail(email);

        if (account != null) {
            return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
