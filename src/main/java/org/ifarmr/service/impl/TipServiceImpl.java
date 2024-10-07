package org.ifarmr.service.impl;

import org.ifarmr.entity.Tip;
import org.ifarmr.repository.TipRepository;
import org.ifarmr.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipServiceImpl implements TipService {
    @Autowired
    private TipRepository tipRepository;

    @Override
    public List<Tip> getAllTips() {
        return tipRepository.findAll();
    }
}
