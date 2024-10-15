package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisors")
public class AdvisorController {

    private final AdvisorService advisorService;

    @Autowired
    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @GetMapping
    public List<Advisor> getAllAdvisors() {
        return advisorService.getAllAdvisors();
    }

    @GetMapping("/{advisorId}")
    public ResponseEntity<Advisor> getAdvisorById(@PathVariable Long advisorId) {
        return advisorService.getAdvisorById(advisorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Advisor> createAdvisor(@RequestBody Advisor advisor) {
        Advisor createdAdvisor = advisorService.createAdvisor(advisor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvisor);
    }

    @PutMapping("/{advisorId}")
    public ResponseEntity<Advisor> updateAdvisor(
            @PathVariable Long advisorId, 
            @RequestBody Advisor advisorDetails) {
        Advisor updatedAdvisor = advisorService.updateAdvisor(advisorId, advisorDetails);
        return ResponseEntity.ok(updatedAdvisor);
    }

    @DeleteMapping("/{advisorId}")
    public ResponseEntity<Void> deleteAdvisor(@PathVariable Long advisorId) {
        advisorService.deleteAdvisor(advisorId);
        return ResponseEntity.noContent().build();
    }
}
