package com.gfa.mounteneering.controller;

import com.gfa.mounteneering.service.ClimberService;
import com.gfa.mounteneering.service.MountainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/climbers")
@RequiredArgsConstructor
public class ClimberController {

    private final ClimberService climberService;
    private final MountainService mountainService;

    @PostMapping
    public String addClimber(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nationality,
            @RequestParam(value = "mountain-id", required = false) Long mountainId,
            RedirectAttributes redirectAttributes) {

        if (
                Objects.isNull(name) || name.isEmpty() ||
                Objects.isNull(nationality) || nationality.isEmpty() ||
                Objects.isNull(mountainId)
        ) {
            redirectAttributes.addFlashAttribute("error", "Error: Missing data!");
        } else {
            climberService.addClimber(name, nationality, mountainService.findMountainById(mountainId).get());
        }
        return "redirect:/";
    }

    @PostMapping("/climb")
    public String climb(RedirectAttributes redirectAttributes,
                        @RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "distance", required = false) Integer distance) {

        try {
            climberService.climb(id, distance);
            redirectAttributes.addFlashAttribute("success", "Success: Climb successful!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: Climb failed!");
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/rescue")
    public String rescue(
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long id) {

        try {
            climberService.rescue(id);
            redirectAttributes.addFlashAttribute("success", "Success: Climber rescued!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: Rescue failed!");
        }
        return "redirect:/";

    }
}
