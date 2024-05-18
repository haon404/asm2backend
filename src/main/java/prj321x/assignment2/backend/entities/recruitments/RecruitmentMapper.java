package prj321x.assignment2.backend.entities.recruitments;

import org.mapstruct.*;
import prj321x.assignment2.backend.entities.categories.Category;
import prj321x.assignment2.backend.entities.recruitments.applies.RecruitmentApply;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecruitmentMapper {
    @Mapping(source = "companyId", target = "company.id")
    Recruitment toEntity(RecruitmentDto recruitmentDto);
    
    @Mapping(target = "categoryIds", expression = "java(categoriesToCategoryIds(recruitment.getCategories()))")
    @Mapping(target = "recruitmentApplyIds", expression = "java(recruitmentAppliesToRecruitmentApplyIds(recruitment.getRecruitmentApplies()))")
    @Mapping(source = "company.id", target = "companyId")
    RecruitmentDto toDto(Recruitment recruitment);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "companyId", target = "company.id")
    Recruitment partialUpdate(RecruitmentDto recruitmentDto, @MappingTarget Recruitment recruitment);
    
    default Set<UUID> categoriesToCategoryIds(Set<Category> categories) {
        return categories.stream().map(Category::getId).collect(Collectors.toSet());
    }
    
    default Set<UUID> recruitmentAppliesToRecruitmentApplyIds(Set<RecruitmentApply> recruitmentApplies) {
        return recruitmentApplies.stream().map(RecruitmentApply::getId).collect(Collectors.toSet());
    }
}