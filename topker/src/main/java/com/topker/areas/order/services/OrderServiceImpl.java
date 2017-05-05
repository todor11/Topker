package com.topker.areas.order.services;

import com.topker.areas.order.entities.Order;
import com.topker.areas.order.models.bindingModels.OrderCreationModel;
import com.topker.areas.order.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public void create(OrderCreationModel orderCreationModel) {
        Order order = this.modelMapper.map(orderCreationModel, Order.class);
        //TODO

        this.orderRepository.save(order);
    }
}
