package prj321x.assignment2.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prj321x.assignment2.backend.entities.categories.CategoryDto;
import prj321x.assignment2.backend.entities.categories.CategoryMapper;
import prj321x.assignment2.backend.entities.categories.CategoryRepository;
import prj321x.assignment2.backend.entities.companies.CompanyDto;
import prj321x.assignment2.backend.entities.companies.CompanyMapper;
import prj321x.assignment2.backend.entities.companies.CompanyRepository;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentDto;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentMapper;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentRepository;
import prj321x.assignment2.backend.entities.utils.PopularRecruitmentDto;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin
public class ServiceController {
    
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper recruitmentMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    
    @GetMapping("/recruitment/popular")
    public ResponseEntity<PopularRecruitmentDto> getPopularRecruitment() {
        RecruitmentDto recruitmentDto;
        try {
            CategoryDto categoryDto = categoryRepository
                    .findCategoryByMostPopular().map(categoryMapper::toDto)
                    .orElseThrow();
            recruitmentDto = recruitmentRepository
                    .findMostAppliedRecruitmentByCategory(categoryDto.id())
                    .map(recruitmentMapper::toDto)
                    .orElseThrow();
            
            PopularRecruitmentDto popularRecruitmentDto = new PopularRecruitmentDto(recruitmentDto, categoryDto);
            
            return ResponseEntity.ok(popularRecruitmentDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/company/popular")
    public ResponseEntity<CompanyDto> getPopularCompany() {
        CompanyDto companyDto;
        try {
            companyDto = companyRepository
                    .findByMostRecruitment().map(companyMapper::toDto)
                    .orElseThrow();
            return ResponseEntity.ok(companyDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
