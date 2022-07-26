package com.example.lesprom.mapper;

import com.example.lesprom.dto.EmployeeDto;
import com.example.lesprom.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    EmployeeDto mapSingle(Employee item);
    @InheritInverseConfiguration
    Employee mapSingle(EmployeeDto dto);

    List<EmployeeDto> mapList(List<Employee> itemList);
    @InheritInverseConfiguration
    List<Employee> mapListInvert(List<EmployeeDto> dtoList);


}
