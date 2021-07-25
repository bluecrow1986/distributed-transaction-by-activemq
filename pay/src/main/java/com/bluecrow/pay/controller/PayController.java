package com.bluecrow.pay.controller;

import com.bluecrow.core.entity.ResponseBo;
import com.bluecrow.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author BlueCrow
 * @Package com.bluecrow.pay.controller
 * @Decription
 * @date 2021/7/25 14:03
 */
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("/createPay")
    public ResponseBo<String> createPay(String name, BigDecimal amount) {

        return ResponseBo.ok(String.valueOf(payService.createPay(name, amount)));
    }

    @PostMapping("/pay")
    public ResponseBo<Integer> pay(String payId) {

        return ResponseBo.ok(payService.pay(Long.parseLong(payId)));
    }

    @PostMapping("/completePay")
    public ResponseBo<Integer> completePay(String payId) {

        return ResponseBo.ok(payService.completePay(Long.parseLong(payId)));
    }
}
