package com.bluecrow.order.component;

import com.alibaba.fastjson.JSONObject;
import com.bluecrow.core.consts.OrderTypeEnum;
import com.bluecrow.core.consts.PayStageEnum;
import com.bluecrow.core.entity.PayEvent;
import com.bluecrow.order.entity.Order;
import com.bluecrow.order.service.OrderService;
import com.bluecrow.order.service.PayEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author BlueCrow
 * @Package com.bluecrow.component
 * @Decription
 * @date 2021/7/24 20:49
 */
@Component
@Slf4j
public class ConsumerQueue {

    @Autowired
    private PayEventService payEventService;

    @Autowired
    private OrderService orderService;

    /**
     * 监听同步支付队列, 用于修改业务内的订单支付状态为支付中
     * @param textMessage
     * @param session
     * @throws JMSException
     */
    @JmsListener(destination = "SyncPayQueue",containerFactory = "jmsListenerContainerFactory")
    @Transactional
    public void receiveSyncPayQueue(TextMessage textMessage, Session session) throws JMSException {
        try {
            log.info("收到的消息："+textMessage.getText());
            String content = textMessage.getText();

            PayEvent payEvent = JSONObject.parseObject(content, PayEvent.class);
            payEvent.setOrderType(OrderTypeEnum.RECEIVED.getCode());
            payEventService.insert(payEvent);
            // 业务内处理
            Order order = new Order();
            order.setStage(PayStageEnum.PAY.getCode());
            order.setPayId(payEvent.getPayId());
            int nCount = orderService.updateOrderStage(order);
            if (nCount > 0) {
                // 业务完成，确认消息 消费成功
                textMessage.acknowledge();
            } else {
                // 回滚消息
                log.error("修改订单表状态(支付中), 失败");
                session.recover();
            }
        }catch (Exception e){
            // 回滚消息
            log.error(e.getMessage(), e);
            session.recover();
        }
    }

    /**
     * 监听异步支付队列, 用于修改业务内的订单支付状态为已支付
     * @param textMessage
     * @param session
     * @throws JMSException
     */
    @JmsListener(destination = "AsyncPayQueue",containerFactory = "jmsListenerContainerFactory")
    @Transactional
    public void receiveAsyncPayQueue(TextMessage textMessage, Session session) throws JMSException {
        try {
            log.info("收到的消息："+textMessage.getText());
            String content = textMessage.getText();

            PayEvent payEvent = JSONObject.parseObject(content, PayEvent.class);
            payEvent.setOrderType(OrderTypeEnum.RECEIVED.getCode());
            payEventService.insert(payEvent);
            // 业务内处理
            Order order = new Order();
            order.setStage(PayStageEnum.PAID.getCode());
            order.setPayId(payEvent.getPayId());
            int nCount = orderService.updateOrderStage(order);
            if (nCount > 0) {
                // 业务完成，确认消息 消费成功
                textMessage.acknowledge();
            } else {
                // 回滚消息
                log.error("修改订单表状态(已支付), 失败");
                session.recover();
            }
        }catch (Exception e){
            // 回滚消息
            log.error(e.getMessage(), e);
            session.recover();
        }
    }

    /**
     * 补偿 处理（人工，脚本）。自己根据自己情况。
     * @param text
     */
    @JmsListener(destination = "DLQ.SyncPayQueue")
    public void receiveDLQSyncPayQueue(String text){
        log.error("死信队列:"+text);
    }

    /**
     * 补偿 处理（人工，脚本）。自己根据自己情况。
     * @param text
     */
    @JmsListener(destination = "DLQ.AsyncPayQueue")
    public void receiveDLQAsyncPayQueue(String text){
        log.error("死信队列:"+text);
    }
}
