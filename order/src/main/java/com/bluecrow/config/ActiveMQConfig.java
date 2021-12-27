package com.bluecrow.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BlueCrow
 * @Package com.bluecrow.config
 * @Decription
 * @date 2021/7/24 19:42
 */
@Configuration
public class ActiveMQConfig {
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("ActiveMQQueue");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    /**
     * <bean id="redeliveryPolicy" class="org.apache.activemq.RedeliveryPolicy">
     *     <!--是否在每次尝试重新发送失败后,增长这个等待时间-->
     *     <property name="useExponentialBackOff" value="true"></property>
     *     <!--重发次数,默认为6次-->
     *     <property name="maximumRedeliveries" value="5"></property>
     *     <!--重发时间间隔,默认为1秒-->
     *     <property name="initialRedeliveryDelay" value="1000"></property>
     *     <!--第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value-->
     *     <property name="backOffMultiplier" value="2"></property>
     *     <!--最大传送延迟，只在useExponentialBackOff为true时有效（V5.5），假设首次重连间隔为10ms，
     *     倍数为2，那么第 二次重连时间间隔为 20ms，第三次重连时间间隔为40ms，
     *     当重连时间间隔大的最大重连时间间隔时，以后每次重连时间间隔都为最大重连时间间隔。-->
     *     <property name="maximumRedeliveryDelay" value="1000"></property>
     * </bean>
     * @return
     */
    /*
    @Bean
    public RedeliveryPolicy redeliveryPolicySetting() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(5);
        redeliveryPolicy.setInitialRedeliveryDelay(1000L);


        return redeliveryPolicy;
    }*/
}
