package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.VIPDao;
import com.pw.grapefarm.model.VipUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;
import static com.pw.grapefarm.common.Response.cResponse;

@Service
public class VIPServiceImpl implements VIPService {

    @Autowired
    VIPDao vipUserDao;

    @Override
    public Response saveVipUser(VipUser user) {
        VipUser vipUser = vipUserDao.findByEmail(user.getEmail());
        if (vipUser == null || vipUser.getEmail() == null || vipUser.getEmail().length() <=0) {
            // 新建一个新的vipuser
            return addVipUser(user);
        }

        return updateVipUser(user);
    }

    @Override
    public Response getVIPUserInfo(String email) {
        VipUser vipUser = vipUserDao.findByEmail(email);
        return new Response(COMMON_SUCCESS_CODE, "成功",vipUser);
    }

    private Date toDateByType(Date date, int field, int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field,amount);
        return calendar.getTime();
    }

    private Response addVipUser(VipUser user) {
        Date startData = new Date();
        Date endData = new Date();
        Integer type = user.getType();
        if (type == 1) {
            // 年卡
            endData = toDateByType(startData,Calendar.YEAR,1);
        } else if (type == 2) {
            // 季卡
            endData = toDateByType(startData,Calendar.MONTH,3);
        }

        user.setStartDate(startData);
        user.setEndDate(endData);

        vipUserDao.save(user);
        return cResponse(COMMON_SUCCESS_CODE, "成功");
    }

    private Response updateVipUser(VipUser user) {

        VipUser vipUser = vipUserDao.findByEmail(user.getEmail());
        Integer type = user.getType();
        Date endData = new Date();
        if (type == 1) {
            // 年卡
            endData = toDateByType(vipUser.getEndDate(),Calendar.YEAR,1);
        } else if (type == 2) {
            // 季卡
            endData = toDateByType(vipUser.getEndDate(),Calendar.MONTH,3);
        }

        user.setEndDate(endData);
        if(vipUser.getStartDate() != null) {
            user.setStartDate(vipUser.getStartDate());
        }


        vipUserDao.modifyByEmail(user.getEndDate(),user.getStartDate(),user.getTransactionId(),user.getType(),user.getEmail());
        return cResponse(COMMON_SUCCESS_CODE, "成功");
    }
}
