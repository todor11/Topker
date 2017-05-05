package com.topker.areas.log.services;

import com.topker.areas.log.entities.Log;
import com.topker.areas.log.models.dtoModels.LogDto;
import com.topker.areas.log.repositories.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @Override
    public void create(LogDto logDto) {
        ModelMapper modelMapper = new ModelMapper();

        Log entity = new Log();
        modelMapper.map(logDto, entity);

        this.logRepository.save(entity);
    }
}
