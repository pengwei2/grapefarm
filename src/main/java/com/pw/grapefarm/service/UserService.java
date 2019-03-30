package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.User;

import javax.transaction.Transactional;

public interface UserService {
    @Transactional
    Response saveUser(User user);

}
