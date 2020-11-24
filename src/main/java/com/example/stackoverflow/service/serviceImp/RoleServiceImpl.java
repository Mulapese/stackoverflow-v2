package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.model.Role;
import com.example.stackoverflow.repository.RoleRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements CRUDService<Role> {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role insert(Role role) {
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
    public Role update(String id, Role role) {
        return null;
    }

    @Override
    public Optional<Role> delete(String id) {
        return Optional.empty();
    }
}
