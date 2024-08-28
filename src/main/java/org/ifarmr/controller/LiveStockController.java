package org.ifarmr.controller;

import org.ifarmr.payload.request.LiveStockRequest;
import org.ifarmr.payload.response.LiveStockResponse;
import org.ifarmr.service.LiveStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/livestock")
public class LiveStockController {

    @Autowired
    private LiveStockService liveStockService;

    @PostMapping("/add")
    public ResponseEntity<LiveStockResponse> addLiveStock(@RequestBody LiveStockRequest liveStockRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        LiveStockResponse addedLiveStock = liveStockService.addLiveStock(liveStockRequest, username);

        return ResponseEntity.status(201).body(addedLiveStock);
    }
}
