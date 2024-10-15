package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisorService {

    private final AdvisorRepository advisorRepository;

    @Autowired
    public AdvisorService(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

    public List<Advisor> getAllAdvisors() {
        return advisorRepository.findAll();
    }

    public Optional<Advisor> getAdvisorById(Long advisorId) {
        return advisorRepository.findById(advisorId);
    }

    public Advisor createAdvisor(Advisor advisor) {
        return advisorRepository.save(advisor);
    }

    public Advisor updateAdvisor(Long advisorId, Advisor advisorDetails) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new RuntimeException("Advisor not found with id " + advisorId));
        advisor.setFirstName(advisorDetails.getFirstName());
        advisor.setLastName(advisorDetails.getLastName());
        advisor.setAddress(advisorDetails.getAddress());
        advisor.setPhone(advisorDetails.getPhone());
        advisor.setEmail(advisorDetails.getEmail());
        return advisorRepository.save(advisor);
    }

    public void deleteAdvisor(Long advisorId) {
        advisorRepository.deleteById(advisorId);
    }
}
