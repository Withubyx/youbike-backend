package nl.novi.youbike_api.service;

import nl.novi.youbike_api.model.Cyclist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class BikeImageService {

    private final Path fileStoragePath;

    public BikeImageService(@Value("${my.upload_location}") String fileStorageLocation) throws IOException {
        this.fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        Files.createDirectories(fileStoragePath);
    }

    public String storeFile(Cyclist cyclist, MultipartFile file) throws IOException {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        cyclist.setBikeImageUploadCounter(cyclist.getBikeImageUploadCounter() + 1);
        String fileName = cyclist.getId() + "-" + cyclist.getBikeImageUploadCounter() + "-" + originalFileName;
        Path path = fileStoragePath.resolve(fileName);

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
