package prj321x.assignment2.backend.entities.companies;

import org.mapstruct.*;
import prj321x.assignment2.backend.entities.recruitments.Recruitment;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    Company toEntity(CompanyDto companyDto);
    
    @Mapping(target = "recruitmentIds", expression = "java(recruitmentsToRecruitmentIds(company.getRecruitments()))")
    CompanyDto toDto(Company company);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Company partialUpdate(CompanyDto companyDto, @MappingTarget Company company);
    
    default Set<UUID> recruitmentsToRecruitmentIds(Set<Recruitment> recruitments) {
        return recruitments.stream().map(Recruitment::getId).collect(Collectors.toSet());
    }
}