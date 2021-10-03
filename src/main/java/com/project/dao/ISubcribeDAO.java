package com.project.dao;

public interface ISubcribeDAO {
    int addSubcribleEmail(String email);
    boolean subcribeEmailExist(String email);
}
