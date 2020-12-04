package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Role;
import com.example.stackoverflow.repository.RoleRepository;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements CRUDService<Role, Role> {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role insert(Account account, Role role) {
        return repository.saveAndFlush(role);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findById(String id) {
        return Optional.empty();
    }

    @Override
    public int update(String id, Role role) {
        return 1;
    }

    @Override
    public Optional<Role> delete(String id) {
        return Optional.empty();
    }
}
