package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Admin;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.AdminDao;
import com.sydneehaley.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




public class AdminServlet extends HttpServlet {
    private AdminService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new AdminService(new AdminDao());
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Admin admin = mapper.readValue(req.getInputStream(), Admin.class);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
//            Admin authenticatedUser = service.authenticateAdmin(admin);
//            resp.setStatus(200);
//            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));

            Admin authenticatedAdmin = service.authenticateAdmin(email, password);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedAdmin));

        } catch(UserNotFoundException e) {
            resp.getWriter().print("Email address not found.");
            resp.setStatus(401);
            resp.setStatus(401, "Your email address in incorrect.");
        } catch(PasswordIncorrectException e) {
            resp.getWriter().print("Incorrect password");
            resp.setStatus(401);
            resp.setStatus(401, "Your password is incorrect.");
        }
    }
}
