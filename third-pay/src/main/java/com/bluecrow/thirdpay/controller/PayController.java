package com.bluecrow.thirdpay.controller;

import com.bluecrow.thirdpay.entity.ResponseBo;
import com.bluecrow.thirdpay.util.HttpClientHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BlueCrow
 * @Package com.bluecrow.thirdpay.controller
 * @Decription
 * @date 2021/7/25 14:45
 */
@RestController
public class PayController {

    private static final String BASE_PAY_URL = "http://localhost:5002/pay/";

    /**
     * 同步支付
     * @return
     */
    @PostMapping("/syncPay")
    public ResponseBo<Integer> syncPay(String payId) {
        // return ResponseBo.responseBo(100, "", null);
        return ResponseBo.ok();
    }

    /**
     * 同步支付
     * @return
     */
    @PostMapping("/asyncPay")
    public ResponseBo<Integer> asyncPay(String payId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("payId", payId);
        ResponseBo responseBo = HttpClientHelper.doPost(BASE_PAY_URL + "completePay", params);
        return ResponseBo.ok();
    }
}
