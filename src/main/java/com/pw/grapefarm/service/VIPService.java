package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.VipUser;

import javax.transaction.Transactional;

public interface VIPService {
    @Transactional
    Response saveVipUser(VipUser user);

    @Transactional
    Response getVIPUserInfo(String email);
}
