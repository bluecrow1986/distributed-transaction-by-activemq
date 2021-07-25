package com.bluecrow.pay.task;

import com.alibaba.fastjson.JSONObject;
import com.bluecrow.core.consts.OrderTypeEnum;
import com.bluecrow.core.consts.SyncFlagEnum;
import com.bluecrow.core.entity.PayEvent;
import com.bluecrow.pay.service.PayEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import javax.jms.Queue;
import java.util.List;

/**
 * @author BlueCrow
 * @Package com.bluecrow.task
 * @Decription
 * @date 2021/7/24 19:46
 */
@Component
@Slf4j
public class ProduceTask {
    @Autowired
    private PayEventService payEventService;

    @Autowired
    private Queue queue;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron="0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task(){
        log.info("定时任务");
        Destination destination = null;
        // 取初始化的事件
        List<PayEvent> payEventList = payEventService.selectByOrderType(OrderTypeEnum.INIT.getCode());
        if (payEventList != null && !payEventList.isEmpty()) {
            for (PayEvent payEvent : payEventList) {
                // 更新初始化事件为已发送
                payEventService.updateSentSuccess(payEvent.getOrderType(), payEvent.getSyncFlag(), OrderTypeEnum.SENT.getCode());
                if (payEvent.getSyncFlag() == SyncFlagEnum.SYNC.getCode()) {
                    // 队列名称为"SyncPayQueue"同步支付队列
                    destination = new ActiveMQQueue("SyncPayQueue");
                } else if (payEvent.getSyncFlag() == SyncFlagEnum.ASYNC.getCode()) {
                    // 队列名称为"AsyncPayQueue"异步支付队列
                    destination = new ActiveMQQueue("AsyncPayQueue");
                }
                jmsMessagingTemplate.convertAndSend(destination, JSONObject.toJSONString(payEvent));
            }
            log.info("修改数据库完成");
        }
    }
}
