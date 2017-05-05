package com.topker.areas.simpleProductDetail.services;


import com.topker.areas.simpleProductDetail.entities.BooleanValueSimpleProductDetail;
import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;
import com.topker.areas.simpleProductDetail.entities.StringValueSimpleProductDetail;
import com.topker.areas.simpleProductDetail.models.bindingModels.SimpleProductDetailCreationModel;
import com.topker.areas.simpleProductDetail.models.viewModels.SimpleBooleanProductDetailViewModel;
import com.topker.areas.simpleProductDetail.models.viewModels.SimpleProductDetailViewModel;
import com.topker.areas.simpleProductDetail.models.viewModels.SimpleStringProductDetailViewModel;
import com.topker.areas.simpleProductDetail.repositories.BooleanValueSimpleProductDetailRepository;
import com.topker.areas.simpleProductDetail.repositories.SimpleProductDetailRepository;
import com.topker.areas.simpleProductDetail.repositories.StringValueSimpleProductDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SimpleProductDetailServiceImpl implements SimpleProductDetailService {

    static final Map<Boolean , List<String>> BOOLEAN_DICTIONARY = new HashMap<Boolean , List<String>>() {{
        List<String> trueDictionary =  new ArrayList<>(
                Arrays.asList("yes", "da", "да", "true", "има"));
        put(true, trueDictionary);
        List<String> falseDictionary =  new ArrayList<>(
                Arrays.asList("no", "ne", "не", "false", "няма"));
        put(false, falseDictionary);
    }};

    private final ModelMapper modelMapper;

    private final SimpleProductDetailRepository simpleProductDetailRepository;

    private final StringValueSimpleProductDetailRepository stringValueSimpleProductDetailRepository;

    private final BooleanValueSimpleProductDetailRepository booleanValueSimpleProductDetailRepository;

    @Autowired
    public SimpleProductDetailServiceImpl(ModelMapper modelMapper, SimpleProductDetailRepository simpleProductDetailRepository,
                                          StringValueSimpleProductDetailRepository stringValueSimpleProductDetailRepository,
                                          BooleanValueSimpleProductDetailRepository booleanValueSimpleProductDetailRepository) {
        this.modelMapper = modelMapper;
        this.simpleProductDetailRepository = simpleProductDetailRepository;
        this.stringValueSimpleProductDetailRepository = stringValueSimpleProductDetailRepository;
        this.booleanValueSimpleProductDetailRepository = booleanValueSimpleProductDetailRepository;
    }

    @Override
    public SimpleProductDetail create(SimpleProductDetailCreationModel simpleProductDetailCreationModel) {
        for (Map.Entry<Boolean, List<String>> booleanListEntry : BOOLEAN_DICTIONARY.entrySet()) {
            if (booleanListEntry.getValue().contains(simpleProductDetailCreationModel.getValue().toLowerCase())) {
                return this.createBooleanSimpleProductDetail(simpleProductDetailCreationModel, booleanListEntry.getKey());

            }
        }

        return this.createStringSimpleProductDetail(simpleProductDetailCreationModel);
    }

    @Override
    public List<SimpleProductDetailViewModel> getAllSimpleProductDetailsByCategoryProductDetailId(Long categoryProductDetailsId) {
        List<SimpleProductDetailViewModel> resultSimpleProductDetail = new ArrayList<>();
        List<SimpleProductDetail> simpleProductDetails = this.simpleProductDetailRepository.findAllByCategoryProductId(categoryProductDetailsId);
        for (SimpleProductDetail simpleProductDetail : simpleProductDetails) {
            if (simpleProductDetail instanceof BooleanValueSimpleProductDetail) {
                SimpleBooleanProductDetailViewModel simpleBooleanProductDetailViewModel = new SimpleBooleanProductDetailViewModel();
                simpleBooleanProductDetailViewModel.setName(simpleProductDetail.getName());
                simpleBooleanProductDetailViewModel.setValue((Boolean) simpleProductDetail.getValue());
                resultSimpleProductDetail.add(simpleBooleanProductDetailViewModel);
            } else if (simpleProductDetail instanceof StringValueSimpleProductDetail) {
                SimpleStringProductDetailViewModel simpleStringProductDetailViewModel = new SimpleStringProductDetailViewModel();
                simpleStringProductDetailViewModel.setName(simpleProductDetail.getName());
                simpleStringProductDetailViewModel.setValue((String) simpleProductDetail.getValue());
                resultSimpleProductDetail.add(simpleStringProductDetailViewModel);
            }
        }

        return resultSimpleProductDetail;
    }

    private SimpleProductDetail createBooleanSimpleProductDetail(SimpleProductDetailCreationModel simpleProductDetailCreationModel, boolean value) {
        BooleanValueSimpleProductDetail booleanValueSimpleProductDetail = this.modelMapper.map(simpleProductDetailCreationModel, BooleanValueSimpleProductDetail.class);
        booleanValueSimpleProductDetail.setValue(value);

        return this.booleanValueSimpleProductDetailRepository.save(booleanValueSimpleProductDetail);
    }

    private SimpleProductDetail createStringSimpleProductDetail(SimpleProductDetailCreationModel simpleProductDetailCreationModel) {
        StringValueSimpleProductDetail stringValueSimpleProductDetail = this.modelMapper.map(simpleProductDetailCreationModel, StringValueSimpleProductDetail.class);

        return this.stringValueSimpleProductDetailRepository.save(stringValueSimpleProductDetail);
    }
}
