package org.ifarmr.controller;


import org.ifarmr.entity.Tip;
import org.ifarmr.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tips")
public class TipController {
    @Autowired
    private TipService tipService;

    @GetMapping
    public List<Tip> getAllTips() {
        return tipService.getAllTips();
    }
}
