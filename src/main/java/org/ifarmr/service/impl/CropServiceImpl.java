package org.ifarmr.service.impl;

import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Crop;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.*;
import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropInfo;
import org.ifarmr.payload.response.CropResponse;
import org.ifarmr.repository.CropRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.CropService;
import org.ifarmr.service.GlobalUploadService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;
    private final GlobalUploadService globalUploadService;

    @Override
    public CropResponse addCrop(CropRequest cropRequest, String username) {
        try {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        // Validate crop name
        if (cropRequest.getCropName() == null || cropRequest.getCropName().isEmpty()) {
            throw new BadRequestException("Crop name cannot be null or empty");
        }

            // Handle photo upload
            String uploadedPhotoUrl = null;
            if (cropRequest.getPhotoUpload() != null && !cropRequest.getPhotoUpload().isEmpty()) {
                uploadedPhotoUrl = globalUploadService.uploadImage(cropRequest.getPhotoUpload());
            }

            Crop crop = Crop.builder()
                    .cropName(cropRequest.getCropName())
                    .cropType(cropRequest.getCropType())
                    .sowDate(cropRequest.getSowDate())
                    .harvestDate(cropRequest.getHarvestDate())
                    .numberOfSeedlings(cropRequest.getNumberOfSeedlings())
                    .costOfSeedlings(cropRequest.getCostOfSeedlings())
                    .wateringFrequency(cropRequest.getWateringFrequency())
                    .fertilizingFrequency(cropRequest.getFertilizingFrequency())
                    .pestAndDiseases(cropRequest.getPestAndDiseases())
                    .photoUpload(uploadedPhotoUrl)
                    .quantity(cropRequest.getQuantity())
                    .location(cropRequest.getLocation())
                    .status(cropRequest.getStatus())
                    .description(cropRequest.getDescription())
                    .user(user)
                    .build();

            Crop savedCrop = cropRepository.save(crop);

            return CropResponse.builder()
                    .responseMessage("Crop added successfully!")
                    .cropInfo(CropInfo.builder()
                            .cropName(savedCrop.getCropName())
                            .cropType(savedCrop.getCropType())
                            .sowDate(LocalDate.from(savedCrop.getSowDate()))
                            .harvestDate(LocalDate.from(savedCrop.getHarvestDate()))
                            .numberOfSeedlings(savedCrop.getNumberOfSeedlings())
                            .costOfSeedlings(savedCrop.getCostOfSeedlings())
                            .wateringFrequency("Every " + savedCrop.getWateringFrequency() + " days")
                            .fertilizingFrequency("Every " + savedCrop.getFertilizingFrequency() + " days")
                            .pestAndDiseases(savedCrop.getPestAndDiseases())
                            .photoUpload(savedCrop.getPhotoUpload())
                            .quantity(savedCrop.getQuantity())
                            .location(savedCrop.getLocation())
                            .status(savedCrop.getStatus())
                            .description(savedCrop.getDescription())
                            .build())
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Data integrity issue: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new IFarmServiceException("An error occurred while adding the crop: " + e.getMessage(), e);
        }
    }
}
