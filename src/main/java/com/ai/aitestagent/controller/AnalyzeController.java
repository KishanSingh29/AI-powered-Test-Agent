package com.ai.aitestagent.controller;

import com.ai.aitestagent.dto.AnalyzeRequest;
import com.ai.aitestagent.service.AnalyzeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @PostMapping
    public String analyze(@RequestBody AnalyzeRequest request) {
        return analyzeService.analyze(request);
    }
}
