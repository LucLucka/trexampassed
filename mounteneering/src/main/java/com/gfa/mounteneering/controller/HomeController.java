package com.gfa.mounteneering.controller;

import com.gfa.mounteneering.service.ClimberService;
import com.gfa.mounteneering.service.MountainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ClimberService climberService;
    private final MountainService mountainService;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("climbers", climberService.findAll());
        model.addAttribute("mountains", mountainService.findAll());

        return "index";
    }

}
