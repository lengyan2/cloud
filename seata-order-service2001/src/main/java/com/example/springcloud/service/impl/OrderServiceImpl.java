package com.example.springcloud.service.impl;

import com.example.springcloud.client.accountClient;
import com.example.springcloud.client.storageClient;
import com.example.springcloud.dao.OrderDao;
import com.example.springcloud.domain.Order;
import com.example.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    accountClient accountClient;
    @Autowired
    storageClient storageClient;

    /**
     * 创建订单 -》 减库存 -》减账号金额 -》 修改订单状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("======下单开始=======");
    // 创建订单
        orderDao.create(order);
        // 远程调用库存服务减库存
        storageClient.reduceStroge(order.getProductId(),order.getCount());
        // 调用账户服务减金额
        accountClient.reduceMoney(order.getUserId(),order.getMoney());
        //修改订单状态为已完成
        orderDao.update(order.getUserId(),0);
    }
}
