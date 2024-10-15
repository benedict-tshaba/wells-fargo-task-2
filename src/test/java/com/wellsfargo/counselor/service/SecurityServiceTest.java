package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.repository.SecurityRepository;
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

public class SecurityServiceTest {

    @Mock
    private SecurityRepository securityRepository;

    @InjectMocks
    private SecurityService securityService;

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
        when(securityRepository.findAll()).thenReturn(List.of(security));

        List<Security> securities = securityService.getAllSecurities();
        assertThat(securities).hasSize(1).contains(security);
    }

    @Test
    void testGetSecurityById() {
        when(securityRepository.findById(1L)).thenReturn(Optional.of(security));

        Optional<Security> found = securityService.getSecurityById(1L);
        assertThat(found).isPresent().contains(security);
    }

    @Test
    void testCreateSecurity() {
        when(securityRepository.save(any(Security.class))).thenReturn(security);

        Security created = securityService.createSecurity(security);
        assertThat(created).isEqualTo(security);
    }

    @Test
    void testUpdateSecurity() {
        when(securityRepository.findById(1L)).thenReturn(Optional.of(security));
        when(securityRepository.save(any(Security.class))).thenReturn(security);

        Security updated = securityService.updateSecurity(1L, security);
        assertThat(updated).isEqualTo(security);
    }

    @Test
    void testDeleteSecurity() {
        doNothing().when(securityRepository).deleteById(1L);
        securityService.deleteSecurity(1L);
        verify(securityRepository, times(1)).deleteById(1L);
    }
}
