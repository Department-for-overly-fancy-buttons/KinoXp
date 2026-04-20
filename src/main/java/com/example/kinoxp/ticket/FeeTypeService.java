package com.example.kinoxp.ticket;

import org.springframework.stereotype.Service;

@Service
public class FeeTypeService implements FeeTypeServiceInterface{

    private final FeeTypeRepository feeTypeRepository;

    FeeTypeService(FeeTypeRepository feeTypeRepository){
        this.feeTypeRepository = feeTypeRepository;
    }

    public FeeType getFeeById(String id){
        return feeTypeRepository.findById(id).get();
    }

    public void createFeeType(FeeType feeType){
        feeTypeRepository.save(feeType);
    }
}
