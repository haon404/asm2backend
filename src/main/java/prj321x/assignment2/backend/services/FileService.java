package prj321x.assignment2.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@AllArgsConstructor
public class FileService {
    
    public String saveFile(MultipartFile file) throws IOException {
        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        
        Path uploadPath = Path.of("src/main/resources/static/images");
        Path filePath = uploadPath.resolve(uniqueName);
        
        if (!Files.exists(filePath)) {
            Files.createDirectories(uploadPath);
        }
        
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }
    
    public void deleteFile(String filePath) throws IOException {
        Files.deleteIfExists(Path.of(filePath));
    }
    
    public byte[] getFile(String profilePicture) throws IOException {
//        get file from profile picture path
        if (profilePicture == null) {
            return null;
        }
        Path filePath = Path.of(profilePicture);
        if (Files.exists(filePath)) {
            return Files.readAllBytes(filePath);
        }
        return null;
    }
}
