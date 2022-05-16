package com.gfa.mounteneering.controller;

import com.gfa.mounteneering.dto.ClimberDto;
import com.gfa.mounteneering.service.ClimberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/climbers")
@RequiredArgsConstructor
public class ApiClimberController {

    private final ClimberService climberService;

    @GetMapping
    public List<ClimberDto> getClimbers(
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "above", required = false, defaultValue = "0") Integer above
    ) {
        return climberService.search(nationality, above);
    }

}
