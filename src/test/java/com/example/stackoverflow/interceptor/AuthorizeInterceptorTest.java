package com.example.stackoverflow.interceptor;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.interceptor.AuthorizeInterceptor.ValidateRequestStatus;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Role;
import com.example.stackoverflow.model.entity.RoleUrl;
import com.example.stackoverflow.model.entity.Url;
import com.example.stackoverflow.repository.RoleUrlRepository;
import com.example.stackoverflow.repository.UrlRepository;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorizeInterceptorTest {

    @Autowired
    AuthorizeInterceptor authorizeInterceptor;

    @MockBean
    AccountServiceImpl accountService;

    @MockBean
    RoleUrlRepository roleUrlRepository;

    @MockBean
    UrlRepository urlRepository;

    @Test
    public void validateRequest_TokenNull_ReachStatement() {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);

        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().role(role).build();

        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        authorizeInterceptor.validateRequest(request);

        verify(roleUrlRepository).findByRoleId(anyInt());
    }

    @Test
    public void validateRequest_TokenNotNullAndAccountNull_ReturnAccountNotFound() {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        request.addHeader("Authorization", "Token");

        given(accountService.getAccountFromToken(anyString())).willReturn(null);
        ValidateRequestStatus result = authorizeInterceptor.validateRequest(request);

        assert (result == ValidateRequestStatus.ACCOUNT_NOT_FOUND);
    }

    @Test
    public void validateRequest_TokenNotNullAndAccountMember_ReachStatement() {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        request.addHeader("Authorization", "Token");
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().role(role).build();

        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        authorizeInterceptor.validateRequest(request);

        verify(roleUrlRepository).findByRoleId(anyInt());
    }

    @Test
    public void validateRequest_TokenNotNullAndAccountModerator_ReachStatement() {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        request.addHeader("Authorization", "Token");
        Role role = Role.builder().roleId(Constant.ROLE_MODERATOR).build();
        Account account = Account.builder().role(role).build();

        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        authorizeInterceptor.validateRequest(request);

        verify(roleUrlRepository).findByRoleId(anyInt());
    }

    @Test
    public void validateRequest_TokenNotNullAndAccountAdmin_ReachStatement() {
        String method = "GET";
        String uri = "/api/questions";
        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
        request.addHeader("Authorization", "Token");
        Role role = Role.builder().roleId(Constant.ROLE_ADMIN).build();
        Account account = Account.builder().role(role).build();

        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        authorizeInterceptor.validateRequest(request);

        verify(roleUrlRepository).findByRoleId(anyInt());
    }

    @Test
    public void isTwoUriEquivalent_Equal_ReturnTrue() {
        String uriDatabase = "/api/questions";
        String uriRequest = "/api/questions";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_FirstAsterisks_ReturnTrue() {
        String uriDatabase = "/*/questions/*";
        String uriRequest = "/api/questions/1";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_LastAsterisks_ReturnTrue() {
        String uriDatabase = "/api/questions/*";
        String uriRequest = "/api/questions/1";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_MiddleAsterisks_ReturnTrue() {
        String uriDatabase = "/api/*/comments";
        String uriRequest = "/api/1/comments";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_TwoAsterisks_ReturnTrue() {
        String uriDatabase = "/api/questions/*/comments/*";
        String uriRequest = "/api/questions/1/comments/2";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_TwoAsterisksConsecutive_ReturnTrue() {
        String uriDatabase = "/api/questions/*/*/*";
        String uriRequest = "/api/questions/1/ab/1f";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_BothEmpty_ReturnTrue() {
        String uriDatabase = "";
        String uriRequest = "";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_OnlyAsterisks_ReturnTrue() {
        String uriDatabase = "/*/*";
        String uriRequest = "/api/12";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (result);
    }

    @Test
    public void isTwoUriEquivalent_DifferentPart_ReturnFalse() {
        String uriDatabase = "/api/questions";
        String uriRequest = "/api/questions/comments";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (!result);
    }

    @Test
    public void isTwoUriEquivalent_DifferentText_ReturnFalse() {
        String uriDatabase = "/api/questions";
        String uriRequest = "/api/questions1";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (!result);
    }

    @Test
    public void isTwoUriEquivalent_DifferentTextAndHasAsterisks_ReturnFalse() {
        String uriDatabase = "/api/questions/*";
        String uriRequest = "/api/questions1/1";

        boolean result = authorizeInterceptor.isTwoUriEquivalent(uriDatabase, uriRequest);

        assert (!result);
    }

    @Test
    public void isRequestValid_UriAndActionMatch_ReturnTrue() {
        String uriRequest = "/api/questions";
        String actionRequest = "POST";
        String uriDatabase = "/api/questions";
        String actionDatabase = "POST";
        RoleUrl roleUrl = RoleUrl.builder().roleId(1).urlId(1).build();
        List<RoleUrl> roleUrls = Arrays.asList(roleUrl);
        int urlId = 1;
        Url url = Url.builder().url(uriDatabase).urlId(urlId).action(actionDatabase).build();

        given(urlRepository.getOne(urlId)).willReturn(url);
        boolean result = authorizeInterceptor.isRequestValid(uriRequest, actionRequest, roleUrls);

        assert (result);
    }

    @Test
    public void isRequestValid_UriMatchAndActionNotMatch_ReturnFalse() {
        String uriRequest = "/api/questions";
        String actionRequest = "POST";
        String uriDatabase = "/api/questions";
        String actionDatabase = "GET";
        RoleUrl roleUrl = RoleUrl.builder().roleId(1).urlId(1).build();
        List<RoleUrl> roleUrls = Arrays.asList(roleUrl);
        int urlId = 1;
        Url url = Url.builder().url(uriDatabase).urlId(urlId).action(actionDatabase).build();

        given(urlRepository.getOne(urlId)).willReturn(url);
        boolean result = authorizeInterceptor.isRequestValid(uriRequest, actionRequest, roleUrls);

        assert (!result);
    }

    @Test
    public void isRequestValid_UriNotMatchAndActionMatch_ReturnFalse() {
        String uriRequest = "/api/questions";
        String actionRequest = "POST";
        String uriDatabase = "/api";
        String actionDatabase = "POST";
        RoleUrl roleUrl = RoleUrl.builder().roleId(1).urlId(1).build();
        List<RoleUrl> roleUrls = Arrays.asList(roleUrl);
        int urlId = 1;
        Url url = Url.builder().url(uriDatabase).urlId(urlId).action(actionDatabase).build();

        given(urlRepository.getOne(urlId)).willReturn(url);
        boolean result = authorizeInterceptor.isRequestValid(uriRequest, actionRequest, roleUrls);

        assert (!result);
    }

}