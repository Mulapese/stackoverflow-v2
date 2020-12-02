package com.example.stackoverflow.controller;

import com.example.stackoverflow.service.implement.TagServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TagController.class)
//@WithMockUser
public class TagControllerTest {

    @MockBean
    private TagServiceImpl service;

    @Test
    public void getTags() {
    }
}