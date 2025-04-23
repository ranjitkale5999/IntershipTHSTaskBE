package com.intership.intershipTHS.service;

import com.intership.intershipTHS.dto.AddressDto;
import com.intership.intershipTHS.entity.Address;
import com.intership.intershipTHS.mapper.AddressMapper;
import com.intership.intershipTHS.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressMapper addressMapper;

    public AddressDto addAddress(AddressDto addressDto){
        Address address=addressMapper.maptoAddress(addressDto);

        Address saveAddress =addressRepo.save(address);

        return addressMapper.maptoAddressDto(saveAddress);
    }

    public List<AddressDto> getAllAddress(){
        List<Address> addresses=addressRepo.findAll();

        return addressMapper.maptoAddressDtos(addresses);
    }



}
