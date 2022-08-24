package com.example.lesprom.mapper;

import com.example.lesprom.dto.employee.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    Employee mapSingle(com.example.lesprom.entity.Employee item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Employee mapSingle(Employee dto);

    List<Employee> mapList(List<com.example.lesprom.entity.Employee> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Employee> mapListInvert(List<Employee> dtoList);


}
