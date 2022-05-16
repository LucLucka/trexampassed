package com.gfa.mounteneering.controller;

import com.gfa.mounteneering.service.MountainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mountains")
@RequiredArgsConstructor
public class MountainController {

    private final MountainService mountainService;

    @GetMapping("/{id}")
    private String getMountains(
            @PathVariable("id") Long id,
            Model model) {

        var mountain = mountainService.findMountainById(id);

        if (mountain.isPresent()) {
            model.addAttribute("mountain", mountain.get());
        } else {
            model.addAttribute("error", "Error: Mountain not found!");
        }

        return "mountains/show";
    }

}
