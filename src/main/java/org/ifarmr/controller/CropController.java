package org.ifarmr.controller;

import jakarta.validation.Valid;
import org.ifarmr.exceptions.BadRequestException;
import org.ifarmr.exceptions.ConflictException;
import org.ifarmr.exceptions.IFarmServiceException;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.CropRequest;
import org.ifarmr.payload.response.CropResponse;
import org.ifarmr.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CropResponse> addCrop(@Valid @ModelAttribute CropRequest cropRequest) {
        try {
            // Get the authenticated user's username
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();


            // Call the service method to add the crop
            CropResponse cropResponse = cropService.addCrop(cropRequest, username);

            // Return the response with HTTP 201 Created
            return new ResponseEntity<>(cropResponse, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new CropResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new CropResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (ConflictException e) {
            return new ResponseEntity<>(new CropResponse(e.getMessage()), HttpStatus.CONFLICT);
        } catch (IFarmServiceException e) {
            return new ResponseEntity<>(new CropResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
