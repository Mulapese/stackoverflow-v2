package com.example.stackoverflow.controller;

import com.example.stackoverflow.model.entity.Role;
import com.example.stackoverflow.service.implement.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Role>> getRoleById(@PathVariable("id") String id) {
        Optional<Role> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role,
                                        @RequestHeader(name = "Authorization") String token) {
        int result = service.insert(token, role);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") String id, @Valid @RequestBody Role role,
                                           @RequestHeader(name = "Authorization") String token) {
        int result = service.update(id, role);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Role>> deleteRole(@PathVariable("id") String id) {
        Optional<Role> entity = service.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
