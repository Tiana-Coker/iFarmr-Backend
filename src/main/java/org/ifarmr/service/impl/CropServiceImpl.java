package org.ifarmr.service.impl;

import org.ifarmr.entity.Crop;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.BadRequestException;
import org.ifarmr.exceptions.ConflictException;
import org.ifarmr.exceptions.IFarmServiceException;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropInfo;
import org.ifarmr.payload.response.CropResponse;
import org.ifarmr.repository.CropRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CropResponse addCrop(CropRequest cropRequest, String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundException("User not found with username: " + username));

            // Validate crop name
            if (cropRequest.getCropName() == null || cropRequest.getCropName().isEmpty()) {
                throw new BadRequestException("Crop name cannot be null or empty");
            }

            // Create a Crop entity from the CropRequest
            Crop crop = Crop.builder()
                    .cropName(cropRequest.getCropName())
                    .cropType(cropRequest.getCropType())
                    .sowDate(cropRequest.getSowDate())
                    .harvestDate(cropRequest.getHarvestDate())
                    .numberOfSeedlings(cropRequest.getNumberOfSeedlings())
                    .costOfSeedlings(cropRequest.getCostOfSeedlings())
                    .wateringFrequency(cropRequest.getWateringFrequency())
                    .fertilisingFrequency(cropRequest.getFertilisingFrequency())
                    .pestAndDiseases(cropRequest.getPestAndDiseases())
                    .photoUpload(cropRequest.getPhotoUpload())
                    .quantity(cropRequest.getQuantity())
                    .location(cropRequest.getLocation())
                    .status(cropRequest.getStatus())
                    .description(cropRequest.getDescription())
                    .user(user) // Set the current user to the crop
                    .build();

            // Save the crop entity to the database
            Crop savedCrop = cropRepository.save(crop);

            // Build and return the response
            return CropResponse.builder()
                    .responseMessage("Crop Added Successfully!")
                    .cropInfo(CropInfo.builder()
                            .cropName(savedCrop.getCropName())
                            .cropType(savedCrop.getCropType())
                            .sowDate(savedCrop.getSowDate().toString())
                            .harvestDate(savedCrop.getHarvestDate().toString())
                            .numberOfSeedlings(savedCrop.getNumberOfSeedlings())
                            .costOfSeedlings(savedCrop.getCostOfSeedlings())
                            .wateringFrequency(savedCrop.getWateringFrequency())
                            .fertilisingFrequency(savedCrop.getFertilisingFrequency())
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
