package nl.novi.youbike_api.service;

import nl.novi.youbike_api.exception.ReadFileException;
import nl.novi.youbike_api.model.Cyclist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

    public Resource downloadFile(String fileName) {
        Path path = fileStoragePath.resolve(fileName);

        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ReadFileException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new ReadFileException("the file doesn't exist or not readable");
        }
    }

    public void deleteFile(String fileName) throws IOException {
        Path path = fileStoragePath.resolve(fileName);

        Files.deleteIfExists(path);
    }
}
