package com.sydneehaley.service;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Admin;
import com.sydneehaley.persistence.AdminDao;


public class AdminService {

    private AdminDao dao;

    public AdminService(AdminDao dao) {
        this.dao = dao;
    }


    public Admin authenticateAdmin(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        return dao.auth(email, password);
    }

}

