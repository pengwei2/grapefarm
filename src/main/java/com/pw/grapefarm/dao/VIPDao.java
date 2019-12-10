package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.VipUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VIPDao extends JpaRepository<VipUser,Integer> {
    VipUser findByEmail(String email);
}
