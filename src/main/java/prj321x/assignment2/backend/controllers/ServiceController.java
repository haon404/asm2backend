package prj321x.assignment2.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prj321x.assignment2.backend.entities.categories.CategoryDto;
import prj321x.assignment2.backend.entities.categories.CategoryMapper;
import prj321x.assignment2.backend.entities.categories.CategoryRepository;
import prj321x.assignment2.backend.entities.companies.CompanyDto;
import prj321x.assignment2.backend.entities.companies.CompanyMapper;
import prj321x.assignment2.backend.entities.companies.CompanyRepository;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentDto;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentMapper;
import prj321x.assignment2.backend.entities.recruitments.RecruitmentRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin
public class ServiceController {
    
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper recruitmentMapper;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    
    @GetMapping("/recruitment/popular")
    public ResponseEntity<RecruitmentDto> getPopularRecruitment() {
        RecruitmentDto recruitmentDto;
        try {
//            recruitmentDto = recruitmentRepository
//                    .findMostAppliedRecruitment().map(recruitmentMapper::toDto)
//                    .orElseThrow();
            CategoryDto categoryDto = categoryRepository
                    .findCategoryByMostPopular().map(categoryMapper::toDto)
                    .orElseThrow();
            recruitmentDto = recruitmentRepository
                    .findMostAppliedRecruitmentByCategory(categoryDto.id())
                    .map(recruitmentMapper::toDto)
                    .orElseThrow();
            
            return ResponseEntity.ok(recruitmentDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable String id) {
        try {
            CompanyDto companyDto = companyRepository
                    .findById(UUID.fromString(id)).map(companyMapper::toDto)
                    .orElseThrow();
            return ResponseEntity.ok(companyDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        try {
            CategoryDto categoryDto = categoryRepository
                    .findById(UUID.fromString(id)).map(categoryMapper::toDto)
                    .orElseThrow();
            return ResponseEntity.ok(categoryDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
