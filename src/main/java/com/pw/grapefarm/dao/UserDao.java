package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
