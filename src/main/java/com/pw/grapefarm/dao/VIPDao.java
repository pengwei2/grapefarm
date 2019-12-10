package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.VipUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface VIPDao extends JpaRepository<VipUser,Integer> {
    VipUser findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update set start_data=?2 and end_date=?3 and type=?4 and transaction_id=?5 where email=?1", nativeQuery = true)
    int modifyVipByEmail(String email, Date startData,Date endData,Integer type,String transactionId);
}
