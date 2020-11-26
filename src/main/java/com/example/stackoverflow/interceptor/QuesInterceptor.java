package com.example.stackoverflow.interceptor;

import com.example.stackoverflow.repository.RoleUrlRepository;
import com.example.stackoverflow.service.serviceImp.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class QuesInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private RoleUrlRepository roleUrlRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//
//        String token = request.getHeader("Authorization");
//
//        // If token = null => No need to authorization
//        // Don't check link get token
//        if (token == null || uri.equals("/authenticate")) {
//            return true;
//        }
//        Account account = accountService.getAccountFromToken(token);
//
//        // If can't get account, maybe wrong token
//        if (account == null) {
//            throw new CommonException("Try another token or contact to admin.");
//        }
//
//        int roleId = account.getRoleByRoleId().getRoleId();
//
//        List<RoleUrl> roleUrls = roleUrlRepository.findByRoleIdAndActionAndUrl(roleId, method, uri);
//        if (roleUrls.size() > 0) {
//            throw new CommonException("Sorry, you don't have permission to access this api.");
//        }
        return true;
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
