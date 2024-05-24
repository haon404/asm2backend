package prj321x.assignment2.backend.entities.companies;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prj321x.assignment2.backend.services.FileService;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final FileService fileService;
    
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") String id) {
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
    
    @PostMapping("/company/{id}/picture")
    @PreAuthorize("hasAnyAuthority('RECRUITER')")
    public ResponseEntity<String> saveProfilePicture(@RequestParam("file") MultipartFile file,
                                                     @PathVariable("id") String id) {
//        Save multipart file to local storage using FileService
        try {
            String fileLocation = fileService.saveFile(file);
            Company company = companyRepository.findById(UUID.fromString(id))
                    .orElseThrow();
            if (company.getCompanyPicture() != null) {
                fileService.deleteFile(company.getCompanyPicture());
            }
            company.setCompanyPicture(fileLocation);
            companyRepository.save(company);
            return ResponseEntity.ok(fileLocation);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/company/{id}/picture")
    @PreAuthorize("hasAnyAuthority('RECRUITER')")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable("id") String id) {
        try {
            Company company = companyRepository.findById(UUID.fromString(id))
                    .orElseThrow();
            return ResponseEntity.ok(fileService.getFile(company.getCompanyPicture()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
