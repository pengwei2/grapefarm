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
    @Query(value = "update  t_vipuser set end_date=?1, start_date=?2 , transaction_id=?3 , type=?4 where email=?5", nativeQuery = true)
    int modifyByEmail(Date endDate,Date startDate,String transactionId , Integer type,String email);

}
