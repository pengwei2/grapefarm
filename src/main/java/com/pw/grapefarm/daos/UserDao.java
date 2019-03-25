package com.pw.grapefarm.daos;

import com.pw.grapefarm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
