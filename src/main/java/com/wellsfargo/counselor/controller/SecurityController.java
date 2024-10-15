package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public List<Security> getAllSecurities() {
        return securityService.getAllSecurities();
    }

    @GetMapping("/{securityId}")
    public ResponseEntity<Security> getSecurityById(@PathVariable Long securityId) {
        return securityService.getSecurityById(securityId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Security> createSecurity(@RequestBody Security security) {
        Security createdSecurity = securityService.createSecurity(security);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSecurity);
    }

    @PutMapping("/{securityId}")
    public ResponseEntity<Security> updateSecurity(
            @PathVariable Long securityId, 
            @RequestBody Security securityDetails) {
        Security updatedSecurity = securityService.updateSecurity(securityId, securityDetails);
        return ResponseEntity.ok(updatedSecurity);
    }

    @DeleteMapping("/{securityId}")
    public ResponseEntity<Void> deleteSecurity(@PathVariable Long securityId) {
        securityService.deleteSecurity(securityId);
        return ResponseEntity.noContent().build();
    }
}
