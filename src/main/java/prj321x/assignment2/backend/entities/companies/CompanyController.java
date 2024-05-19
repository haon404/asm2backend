package prj321x.assignment2.backend.entities.companies;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor
@CrossOrigin
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    
    @GetMapping("/{id}")
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
}
