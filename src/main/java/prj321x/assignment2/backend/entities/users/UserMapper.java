package prj321x.assignment2.backend.entities.users;

import org.mapstruct.*;
import prj321x.assignment2.backend.entities.recruitments.applies.RecruitmentApply;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "roleRoleName", target = "role.roleName")
    User toEntity(UserDto userDto);
    
    @Mapping(target = "recruitmentApplyIds", expression = "java(recruitmentAppliesToRecruitmentApplyIds(user.getRecruitmentApplies()))")
    @Mapping(source = "role.roleName", target = "roleRoleName")
    UserDto toDto(User user);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "roleRoleName", target = "role.roleName")
    User partialUpdate(UserDto userDto, @MappingTarget User user);
    
    default Set<UUID> recruitmentAppliesToRecruitmentApplyIds(Set<RecruitmentApply> recruitmentApplies) {
        return recruitmentApplies.stream().map(RecruitmentApply::getId).collect(Collectors.toSet());
    }
}