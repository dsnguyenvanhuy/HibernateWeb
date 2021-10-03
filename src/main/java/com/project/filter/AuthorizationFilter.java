package com.project.filter;

import com.project.pojo.Account;
import com.project.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context =filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith(request.getContextPath() + "/admin")){
            Account account = (Account) SessionUtil.getInstance().getValue(request,"acc");
            if (account != null){
                if (account.getRole() == 1){
                    chain.doFilter(request,response);
                }else if (account.getRole() == 0){
                    response.sendRedirect(request.getContextPath() + "/login?action=signin");
                }
            }else {
                response.sendRedirect(request.getContextPath() + "/login?action=signin");
            }
        }else {
            chain.doFilter(request,response);
        }
    }
}
