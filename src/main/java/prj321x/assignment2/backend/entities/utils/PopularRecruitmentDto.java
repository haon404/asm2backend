package prj321x.assignment2.backend.entities.utils;

import prj321x.assignment2.backend.entities.categories.CategoryDto;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentDto;

import java.io.Serializable;

public record PopularRecruitmentDto(RecruitmentDto recruitment, CategoryDto category) implements Serializable {

}
