package com.example.stackoverflow.controller;

import com.example.stackoverflow.model.Tag;
import com.example.stackoverflow.service.implement.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tag>> getTagById(@PathVariable("id") String id) {
        Optional<Tag> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping
//    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
//        int result = service.insert(tag);
//
//        if (result == 0) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        return new ResponseEntity<>(tag, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") String id, @Valid @RequestBody Tag tag) {
        int result = service.update(id, tag);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Tag>> deleteTag(@PathVariable("id") String id) {
        Optional<Tag> entity = service.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
