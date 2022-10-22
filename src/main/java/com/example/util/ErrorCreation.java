package com.example.util;

import com.example.entity.User;
import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ErrorCreation {

    @Autowired
    private UserRepo userRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setError1(User savedUserInfo){
        savedUserInfo.setMobile("7777788888");
		userRepo.save(savedUserInfo);
        System.out.println(10/0);
    }
}
