package com.example.stackoverflow.interceptor;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.exception.exceptionType.MessageException;
import com.example.stackoverflow.model.RoleUrl;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.repository.RoleUrlRepository;
import com.example.stackoverflow.repository.UrlRepository;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private RoleUrlRepository roleUrlRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();

        String token = request.getHeader("Authorization");
        int roleId = Constant.ROLE_GUEST;

        // Authorize for role if is different guest (don's have token)
        if (token != null) {
            Account account = accountService.getAccountFromToken(token);
            // If can't get account, maybe wrong token
            if (account == null) {
                throw new MessageException("Please authenticate again or contact to admin.");
            }
            roleId = account.getRole().getRoleId();
        }

        // Get all url which roleId can access
        List<RoleUrl> roleUrls = roleUrlRepository.findByRoleId(roleId);
        if (isRequestValid(uri, method, roleUrls)) {
            return true;
        } else {
            throw new MessageException("Sorry, you don't have permission to access this api.");
        }
    }

    // "/question/*/answers/*" (uriDatabase) will equivalent "/questions/1/answers/1y" (uriRequest)
    public boolean isTwoUriEquivalent(String uriDatabase, String uriRequest) {
        String[] uriDatabasePart = uriDatabase.split("/");
        String[] uriRequestPart = uriRequest.split("/");

        if (uriDatabasePart.length != uriRequestPart.length) {
            return false;
        }

        for (int i = 0; i < uriDatabasePart.length; i++) {
            if (!uriDatabasePart[i].equals("*") && !uriDatabasePart[i].equals(uriRequestPart[i])) {
                return false;
            }
        }
        return true;
    }

    // Request valid when is equivalent in uri and equal in action (POST, PUT, POST, ...)
    public boolean isRequestValid(String uriRequest, String action, List<RoleUrl> roleUrls) {
        for (int i = 0; i < roleUrls.size(); i++) {
            String uriDatabase = urlRepository.getOne(roleUrls.get(i).getUrlId()).getUrl();
            String actionDatabase = urlRepository.getOne(roleUrls.get(i).getUrlId()).getAction();
            if (isTwoUriEquivalent(uriDatabase, uriRequest) && actionDatabase.equals(action)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        System.out.println("After completion method is Calling");
    }

}
