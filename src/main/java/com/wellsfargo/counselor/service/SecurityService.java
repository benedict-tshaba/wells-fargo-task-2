package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {

    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public List<Security> getAllSecurities() {
        return securityRepository.findAll();
    }

    public Optional<Security> getSecurityById(Long securityId) {
        return securityRepository.findById(securityId);
    }

    public Security createSecurity(Security security) {
        return securityRepository.save(security);
    }

    public Security updateSecurity(Long securityId, Security securityDetails) {
        Security security = securityRepository.findById(securityId)
                .orElseThrow(() -> new RuntimeException("Security not found with id " + securityId));
        security.setPortfolio(securityDetails.getPortfolio());
        security.setName(securityDetails.getName());
        security.setCategory(securityDetails.getCategory());
        security.setPurchaseDate(securityDetails.getPurchaseDate());
        security.setPurchasePrice(securityDetails.getPurchasePrice());
        security.setQuantity(securityDetails.getQuantity());
        return securityRepository.save(security);
    }

    public void deleteSecurity(Long securityId) {
        securityRepository.deleteById(securityId);
    }
}
