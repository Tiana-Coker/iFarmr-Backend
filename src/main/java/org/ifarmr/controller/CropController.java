package org.ifarmr.controller;

import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropResponse;
import org.ifarmr.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping("/add")
    public ResponseEntity<CropResponse> addCrop(@RequestBody CropRequest cropRequest) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();


    CropResponse addedCrop = cropService.addCrop(cropRequest, username);

        return ResponseEntity.status(201).body(addedCrop);
}
}
