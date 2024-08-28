package org.ifarmr.service.impl;

import org.ifarmr.entity.LiveStock;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.BadRequestException;
import org.ifarmr.exceptions.ConflictException;
import org.ifarmr.exceptions.IFarmServiceException;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.LiveStockRequest;
import org.ifarmr.payload.response.LiveStockInfo;
import org.ifarmr.payload.response.LiveStockResponse;
import org.ifarmr.repository.LiveStockRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.LiveStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class LiveStockServiceImpl implements LiveStockService {

    @Autowired
    private LiveStockRepository liveStockRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LiveStockResponse addLiveStock(LiveStockRequest liveStockRequest, String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundException("User not found with username: " + username));

            // Validate animal name
            if (liveStockRequest.getAnimalName() == null || liveStockRequest.getAnimalName().isEmpty()) {
                throw new BadRequestException("Animal name cannot be null or empty");
            }

            // Create a LiveStock entity from the LiveStockRequest
            LiveStock liveStock = LiveStock.builder()
                    .animalName(liveStockRequest.getAnimalName())
                    .animalType(liveStockRequest.getAnimalType())
                    .breed(liveStockRequest.getBreed())
                    .quantity(liveStockRequest.getQuantity())
                    .age(liveStockRequest.getAge())
                    .wateringFrequency(liveStockRequest.getWateringFrequency())
                    .feedingSchedule(liveStockRequest.getFeedingSchedule())
                    .vaccinationSchedule(liveStockRequest.getVaccinationSchedule())
                    .healthIssues(liveStockRequest.getHealthIssues())
                    .description(liveStockRequest.getDescription())
                    .status(liveStockRequest.getStatus())
                    .photoUpload(liveStockRequest.getPhotoUpload())
                    .user(user) // Set the current user to the livestock
                    .build();

            // Save the livestock entity to the database
            LiveStock savedLiveStock = liveStockRepository.save(liveStock);

            // Build and return the response
            return LiveStockResponse.builder()
                    .responseMessage("Livestock Added Successfully!")
                    .liveStockInfo(LiveStockInfo.builder()
                            .animalName(savedLiveStock.getAnimalName())
                            .animalType(savedLiveStock.getAnimalType())
                            .breed(savedLiveStock.getBreed())
                            .quantity(savedLiveStock.getQuantity())
                            .age(savedLiveStock.getAge())
                            .wateringFrequency(savedLiveStock.getWateringFrequency())
                            .feedingSchedule(savedLiveStock.getFeedingSchedule())
                            .vaccinationSchedule(savedLiveStock.getVaccinationSchedule())
                            .healthIssues(savedLiveStock.getHealthIssues())
                            .description(savedLiveStock.getDescription())
                            .status(savedLiveStock.getStatus())
                            .photoUpload(savedLiveStock.getPhotoUpload())
                            .build())
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Data integrity issue: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new IFarmServiceException("An error occurred while adding the livestock: " + e.getMessage(), e);
        }
    }
}
