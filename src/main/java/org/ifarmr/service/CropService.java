package org.ifarmr.service;

import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropResponse;

public interface CropService {
    CropResponse addCrop(CropRequest cropRequest, String username);
}
 