package prj321x.assignment2.backend.entities.users;

import org.mapstruct.*;
import prj321x.assignment2.backend.controllers.RegisterDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegisterMapper {
    @Mapping(source = "roleRoleName", target = "role.roleName")
    User toEntity(RegisterDto registerDto);
    
    @Mapping(source = "role.roleName", target = "roleRoleName")
    RegisterDto toDto(User user);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "roleRoleName", target = "role.roleName")
    User partialUpdate(RegisterDto registerDto, @MappingTarget User user);
}