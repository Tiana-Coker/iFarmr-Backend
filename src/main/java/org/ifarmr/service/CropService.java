package org.ifarmr.service;

import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropResponse;
import org.ifarmr.payload.response.CropSummaryResponse;
import org.ifarmr.payload.response.CropsResponse;

import java.util.List;

public interface CropService {

    CropResponse addCrop(CropRequest cropRequest, String username);

    List<CropResponse> getAllCropsByUser(String username);

    List<CropSummaryResponse> getCropSummaryByUser(String username);

    int totalCrop(String username);

    // Method to get all crops for a user
    CropsResponse getAllCrops(String username);
}
 