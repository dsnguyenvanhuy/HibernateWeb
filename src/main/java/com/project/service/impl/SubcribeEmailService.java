package com.project.service.impl;

import com.project.dao.ISubcribeDAO;
import com.project.service.IAccountService;
import com.project.service.ISubcribeEmailService;

import javax.inject.Inject;

public class SubcribeEmailService implements ISubcribeEmailService {
    @Inject
    private ISubcribeDAO subcribeDAO;
    @Inject
    private IAccountService accountService;
    @Override
    public int addSubcribleEmail(String email) {
        boolean check = accountService.existEmail(email) || subcribeDAO.subcribeEmailExist(email);
        if (check){// existed
            return -1;
        }
        // not existed
        subcribeDAO.addSubcribleEmail(email);
        return 1;
    }
}
