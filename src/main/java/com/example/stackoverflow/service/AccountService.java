package com.example.stackoverflow.service;

//import com.example.stackoverflow.exception.RecordNotFoundException;
//import com.example.stackoverflow.exception.WrongHeaderInfoException;
import com.example.stackoverflow.exception.RecordNotFoundException;
import com.example.stackoverflow.exception.WrongHeaderInfoException;
import com.example.stackoverflow.model.AccountEntity;
import com.example.stackoverflow.repository.AccountRepository;
import com.example.stackoverflow.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountService implements CRUDService<AccountEntity> {

    @Autowired
    private AccountRepository repository;

    // Not validate yet
    @Override
    public AccountEntity insert(AccountEntity accountEntity) {
        String id = String.valueOf(accountEntity.getAccountId());

        if(findById(id).isPresent()){
            return null;
        }

        if (accountEntity.getCreatedTime() == null) {
            accountEntity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        }
        return repository.save(accountEntity);
    }

    @Override
    public List<AccountEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AccountEntity> findById(String id) {
        return repository.findById(Integer.parseInt(id));
    }

    @Override
    public AccountEntity update(String id, AccountEntity accountEntity) {


        if (!findById(id).isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("accountId"));
        }
        if(!id.equals(accountEntity.getAccountId() + "")){
            throw new WrongHeaderInfoException(ErrorMessage.notMatch("accountId"));
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<AccountEntity>> violations = validator.validate(accountEntity);
        for (ConstraintViolation<AccountEntity> violation : violations) {
            System.out.println(violation.getMessage());
        }
        accountEntity.setAccountId(Integer.parseInt(id));
        return repository.save(accountEntity);
    }

    @Override
    public Optional<AccountEntity> delete(String id) {
        int idInt = Integer.parseInt(id);
        Optional<AccountEntity> accountEntity = repository.findById(idInt);
        repository.deleteById(idInt);
        return accountEntity;
    }
}
