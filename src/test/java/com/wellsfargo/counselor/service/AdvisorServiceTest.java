package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.repository.AdvisorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvisorServiceTest {

    @Mock
    private AdvisorRepository advisorRepository;

    @InjectMocks
    private AdvisorService advisorService;

    private Advisor advisor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
    }

    @Test
    void testGetAllAdvisors() {
        when(advisorRepository.findAll()).thenReturn(List.of(advisor));

        List<Advisor> advisors = advisorService.getAllAdvisors();
        assertThat(advisors).hasSize(1).contains(advisor);
    }

    @Test
    void testGetAdvisorById() {
        when(advisorRepository.findById(1L)).thenReturn(Optional.of(advisor));

        Optional<Advisor> found = advisorService.getAdvisorById(1L);
        assertThat(found).isPresent().contains(advisor);
    }

    @Test
    void testCreateAdvisor() {
        when(advisorRepository.save(any(Advisor.class))).thenReturn(advisor);

        Advisor created = advisorService.createAdvisor(advisor);
        assertThat(created).isEqualTo(advisor);
    }

    @Test
    void testUpdateAdvisor() {
        when(advisorRepository.findById(1L)).thenReturn(Optional.of(advisor));
        when(advisorRepository.save(any(Advisor.class))).thenReturn(advisor);

        Advisor updated = advisorService.updateAdvisor(1L, advisor);
        assertThat(updated).isEqualTo(advisor);
    }

    @Test
    void testDeleteAdvisor() {
        doNothing().when(advisorRepository).deleteById(1L);
        advisorService.deleteAdvisor(1L);
        verify(advisorRepository, times(1)).deleteById(1L);
    }
}
