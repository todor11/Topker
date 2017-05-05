package com.topker.areas.singleProductOrder.services;

import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import com.topker.areas.singleProductOrder.models.bindingModels.SingleProductOrderCreationModel;
import com.topker.areas.singleProductOrder.repositories.SingleProductOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingleProductOrderServiceImpl implements SingleProductOrderService {

    private final ModelMapper modelMapper;

    private final SingleProductOrderRepository singleProductOrderRepository;

    @Autowired
    public SingleProductOrderServiceImpl(ModelMapper modelMapper, SingleProductOrderRepository singleProductOrderRepository) {
        this.modelMapper = modelMapper;
        this.singleProductOrderRepository = singleProductOrderRepository;
    }

    @Override
    public SingleProductOrder create(SingleProductOrderCreationModel singleProductOrderCreationModel) {
        SingleProductOrder singleProductOrder = this.modelMapper.map(singleProductOrderCreationModel, SingleProductOrder.class);
        //TODO

        return this.singleProductOrderRepository.save(singleProductOrder);
    }
}
