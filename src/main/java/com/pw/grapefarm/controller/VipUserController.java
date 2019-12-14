package com.pw.grapefarm.controller;


import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.RecordDao;
import com.pw.grapefarm.dao.VIPDao;
import com.pw.grapefarm.model.Record;
import com.pw.grapefarm.model.VipUser;
import com.pw.grapefarm.service.VIPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;


@Api(value = "VIP用户",tags = {"VIP用户接口"})
@RestController
@RequestMapping("/vipuser")
public class VipUserController extends BaseController  {
    private static int maxFreeCount = 10;

    @Autowired
    VIPService vipService;

    @Autowired
    VIPDao vipDao;

    @Autowired
    RecordDao recordDao;

    @ApiOperation(value = "获取用户的VIP信息")
    @GetMapping
    public Response getVIPInfo(HttpServletRequest request, @RequestParam(value = "email") String email) {
        return vipService.getVIPUserInfo(email);
    }


    @ApiOperation(value = "获取用户是否是vip或者是否提交了10次记录",notes = "status = " +
                    "'0' 提交次数少于10次的;" +
                    "'1' 提交次数大于等于10次且未购买VIP;" +
                    "'2' 提交次数大于10次且购买VIP已到期;" +
                    "'3' 提交次数大于10次且购买VIP未到期" )
    @GetMapping(value = "/vipstatus")
    public Response isVIP(HttpServletRequest request) {
        String email = getUserName(request);
        VipUser vipUser = vipDao.findByEmail(email);
        List<Record>  records = recordDao.findByUsername(email);
        Map<String,String > map = new HashMap<>();
        if (records == null) {
            // 没有提交记录
            map.put("status","0");
        } else  {
            if(records.size() < maxFreeCount) {
                map.put("status", "0");
            } else {
                if(vipUser == null) {
                    if(records.size() >= maxFreeCount) {
                        map.put("status","1");
                    }
                } else {
                    Date newDate = new Date();
                    if (vipUser.getEmail()!= null && newDate.getTime() > vipUser.getEndDate().getTime() ) {
                        map.put("status","2");
                    } else if (vipUser.getEmail()!= null && newDate.getTime() <= vipUser.getEndDate().getTime()) {
                        map.put("status","3");
                    }
                }
            }
        }

        return new Response(COMMON_SUCCESS_CODE, "成功", map);
    }

    @ApiOperation(value = "保存VIP用户")
    @PostMapping
    public Response saveVIPUser(@Valid @RequestBody VipUser user, BindingResult bindingResult, HttpServletRequest request) {
        return vipService.saveVipUser(user);
    }
}
