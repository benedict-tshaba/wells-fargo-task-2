package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.service.AdvisorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvisorControllerTest {

    @Mock
    private AdvisorService advisorService;

    @InjectMocks
    private AdvisorController advisorController;

    private Advisor advisor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
    }

    @Test
    void testGetAllAdvisors() {
        when(advisorService.getAllAdvisors()).thenReturn(List.of(advisor));

        List<Advisor> found = advisorController.getAllAdvisors();
        assertThat(found).hasSize(1).contains(advisor);
    }

    @Test
    void testGetAdvisorById() {
        when(advisorService.getAdvisorById(1L)).thenReturn(Optional.of(advisor));

        ResponseEntity<Advisor> response = advisorController.getAdvisorById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(advisor);
    }

    @Test
    void testCreateAdvisor() {
        when(advisorService.createAdvisor(any(Advisor.class))).thenReturn(advisor);

        ResponseEntity<Advisor> response = advisorController.createAdvisor(advisor);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(advisor);
    }

    @Test
    void testUpdateAdvisor() {
        when(advisorService.updateAdvisor(anyLong(), any(Advisor.class))).thenReturn(advisor);

        ResponseEntity<Advisor> response = advisorController.updateAdvisor(1L, advisor);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(advisor);
    }

    @Test
    void testDeleteAdvisor() {
        ResponseEntity<Void> response = advisorController.deleteAdvisor(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(advisorService, times(1)).deleteAdvisor(1L);
    }
}
