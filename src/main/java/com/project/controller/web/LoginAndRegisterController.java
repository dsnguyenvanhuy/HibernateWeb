package com.project.controller.web;

import com.project.pojo.Account;
import com.project.service.IAccountService;
import com.project.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginAndRegisterController", urlPatterns = {"/login","/register","/logout"})
public class LoginAndRegisterController extends HttpServlet {
    @Inject
    private IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action == null){//logout
            SessionUtil.getInstance().removeValue(request,"acc");
            response.sendRedirect("trang-chu");
        }else if (action.equals("signin")){// login
            request.getRequestDispatcher("/decorators/login.jsp").forward(request,response);
        }else if (action.equals("signup")){//register
            request.getRequestDispatcher("/decorators/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        String mess = "";
        if (action.equals("signin")){//login
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            Account account = accountService.checkLogin(email,pass);
            if (account != null){//true to login
                SessionUtil.getInstance().putValue(request,"acc",account);
                if (account.getRole() == 1){
                    response.sendRedirect("admin-home");
                }else {
                    response.sendRedirect("trang-chu");
                }

            }else {
                mess = "email or password is not correct";
                request.setAttribute("mess",mess);
                request.getRequestDispatcher("/decorators/login.jsp").forward(request,response);
            }
        }else if (action.equals("signup")){//register
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            if (accountService.existEmail(email)){// email existed
                mess = "email is invalid";
                request.setAttribute("mess",mess);
                request.getRequestDispatcher("/decorators/register.jsp").forward(request,response);
            }else {// email not existed
                if (password.equals(repassword)){// add new account
                    Account account = new Account(email,password,0,fullname,address,phone);
                    account  = accountService.addNewAccount(account);
                    SessionUtil.getInstance().putValue(request,"acc",account);
                    response.sendRedirect("trang-chu");
                }else {
                    mess = "password and repassword is not correct";
                    request.setAttribute("mess",mess);
                    request.getRequestDispatcher("/decorators/register.jsp").forward(request,response);
                }
            }
        }
    }
}
