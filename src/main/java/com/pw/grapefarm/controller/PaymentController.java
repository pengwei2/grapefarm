package com.pw.grapefarm.controller;


import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.pw.grapefarm.common.util.URLUtils;
import com.pw.grapefarm.config.PaypalPaymentIntent;
import com.pw.grapefarm.config.PaypalPaymentMethod;
import com.pw.grapefarm.model.VipRecord;
import com.pw.grapefarm.model.VipUser;
import com.pw.grapefarm.service.PaypalService;
import com.pw.grapefarm.service.VIPRecordService;
import com.pw.grapefarm.service.VIPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseController {
    public static final String PAYPAL_SUCCESS_URL = "/pay/success";
    public static final String PAYPAL_CANCEL_URL = "/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private VIPService vipService;

    @Autowired
    private VIPRecordService vipRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "pay";
    }

    //生成指定length的随机字符串（A-Z，a-z，0-9）
    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    // 通过h5的form提交的post方式调用接口
    @RequestMapping(method = RequestMethod.POST)
    public String pay(HttpServletRequest request,
                      @RequestParam("amount") Double amount){
        String cancelUrl = URLUtils.getBaseURl(request)  + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl(request) + PAYPAL_SUCCESS_URL;

        String order_desc = amount + "_" + getRandomString(10);

        try {
            Payment payment = paypalService.createPayment(
                    amount,
                    "AUD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    order_desc,
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
    public String cancelPay(){
        return "failure";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/successto")
    public String successTo(@RequestParam("amount") String paymentId,
                             @RequestParam("serial") String payerId){

        return "successfully";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/success")
    public String successPay(HttpServletRequest request,
                             @RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){

                // 订单总价
                String total = payment.getTransactions().get(0).getAmount().getTotal();
                // 交易号
                String transationId = payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId();
                String payInfo = "?amount=" + total + "&serial=" + transationId;


                String email = getUserName(request);
                // 保存交易记录
                VipRecord vipRecord = new VipRecord();
                vipRecord.setVipDate(new Date());
                vipRecord.setEmail(email);
                vipRecord.setAmount(total);
                vipRecord.setPayerId(payerId);
                vipRecord.setPaymentId(paymentId);
                vipRecord.setTransactionId(transationId);
                vipRecordService.saveRecord(vipRecord);

                // 保存vip用户数据
                VipUser vipUser = new VipUser();
                vipUser.setEmail(email);
                vipUser.setTransactionId(transationId);
                if (total.startsWith("199")) {
                    // 年卡
                    vipUser.setType(1);
                } else if (total.startsWith("89")) {
                    vipUser.setType(2);
                }

                vipService.saveVipUser(vipUser);

                return "redirect:" + "successto" + payInfo;
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
