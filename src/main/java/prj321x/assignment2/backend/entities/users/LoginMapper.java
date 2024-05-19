package prj321x.assignment2.backend.entities.users;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginMapper {
    User toEntity(LoginDto loginDto);
    
    LoginDto toDto(User user);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(LoginDto loginDto, @MappingTarget User user);
}