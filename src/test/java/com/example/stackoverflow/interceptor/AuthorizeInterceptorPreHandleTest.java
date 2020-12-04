package com.example.stackoverflow.interceptor;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.exception.exceptionType.MessageException;
import com.example.stackoverflow.repository.RoleUrlRepository;
import com.example.stackoverflow.repository.UrlRepository;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorizeInterceptorPreHandleTest {
    @Spy
    AuthorizeInterceptor authorizeInterceptor;

    @MockBean
    AccountServiceImpl accountService;

    @MockBean
    RoleUrlRepository roleUrlRepository;

    @MockBean
    UrlRepository urlRepository;

    @Test
    public void preHandle_SUCCESS_ReturnTrue() throws Exception {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handle = new Object();

        // Call method
//        Mockito.when(authorizeInterceptor.preHandle(request, response, handle)).thenReturn(true);
        // Not call method
        Mockito.doReturn(AuthorizeInterceptor.ValidateRequestStatus.SUCCESS).when(authorizeInterceptor).validateRequest(request);
        boolean result = authorizeInterceptor.preHandle(request, response, handle);
        assert(result);
    }

    @Test
    public void preHandle_AccountNotFound_ThrowException() throws Exception {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handle = new Object();

        Mockito.doReturn(AuthorizeInterceptor.ValidateRequestStatus.ACCOUNT_NOT_FOUND).when(authorizeInterceptor).validateRequest(request);

        Exception exception = Assertions.assertThrows(MessageException.class, () -> {
            authorizeInterceptor.preHandle(request, response, handle);
        });

        String expectedMessage = "Please authenticate again or contact to admin.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void preHandle_DontHavePermission_ThrowException() throws Exception {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handle = new Object();

        Mockito.doReturn(AuthorizeInterceptor.ValidateRequestStatus.DONT_HAVE_PERMISSION).when(authorizeInterceptor).validateRequest(request);

        Exception exception = Assertions.assertThrows(MessageException.class, () -> {
            authorizeInterceptor.preHandle(request, response, handle);
        });

        String expectedMessage = "Sorry, you don't have permission to access this api.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}
