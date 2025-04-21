package com.intership.intershipTHS.mapper;

import com.intership.intershipTHS.dto.MobileNumberDto;
import com.intership.intershipTHS.entity.MobileNumber;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MobileNumberMapper {
    MobileNumberDto mapMobileNumberDto(MobileNumber mobileNumber);
    MobileNumber mapMobileNumber(MobileNumberDto mobileNumberDto);

    List<MobileNumberDto> maptoMobileNumberDtos(List<MobileNumber>mobileNumbers);
    List<MobileNumber> maptoMobileNumbers(List<MobileNumberDto>mobileNumberDtos);
}