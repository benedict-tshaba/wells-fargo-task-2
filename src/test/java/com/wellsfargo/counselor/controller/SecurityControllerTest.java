package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.service.SecurityService;
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

public class SecurityControllerTest {

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private SecurityController securityController;

    private Security security;
    private Portfolio portfolio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        Client client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        portfolio = new Portfolio(client);
        security = new Security(portfolio, "AAPL", "Stock", "2023-01-01", 150.00, 10);
    }

    @Test
    void testGetAllSecurities() {
        when(securityService.getAllSecurities()).thenReturn(List.of(security));

        List<Security> found = securityController.getAllSecurities();
        assertThat(found).hasSize(1).contains(security);
    }

    @Test
    void testGetSecurityById() {
        when(securityService.getSecurityById(1L)).thenReturn(Optional.of(security));

        ResponseEntity<Security> response = securityController.getSecurityById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(security);
    }

    @Test
    void testGetSecurityById_NotFound() {
        when(securityService.getSecurityById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Security> response = securityController.getSecurityById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCreateSecurity() {
        when(securityService.createSecurity(any(Security.class))).thenReturn(security);

        ResponseEntity<Security> response = securityController.createSecurity(security);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(security);
    }

    @Test
    void testUpdateSecurity() {
        when(securityService.updateSecurity(anyLong(), any(Security.class))).thenReturn(security);

        ResponseEntity<Security> response = securityController.updateSecurity(1L, security);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(security);
    }

    @Test
    void testDeleteSecurity() {
        ResponseEntity<Void> response = securityController.deleteSecurity(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(securityService, times(1)).deleteSecurity(1L);
    }
}
