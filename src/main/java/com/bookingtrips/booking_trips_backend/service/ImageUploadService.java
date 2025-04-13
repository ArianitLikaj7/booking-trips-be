package com.bookingtrips.booking_trips_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }

    public void deleteImageByUrl(String imageUrl) {
        try {
            String publicId = extractPublicIdFromUrl(imageUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException("Nuk u fshi dot imazhi: " + imageUrl, e);
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        String[] split = imageUrl.split("/upload/");
        if (split.length < 2) {
            throw new IllegalArgumentException("URL e pavlefshme: " + imageUrl);
        }
        String path = split[1];
        String withoutVersion = path.substring(path.indexOf("/") + 1); // folder/photo.jpg
        return withoutVersion.substring(0, withoutVersion.lastIndexOf('.')); // folder/photo
    }
}
