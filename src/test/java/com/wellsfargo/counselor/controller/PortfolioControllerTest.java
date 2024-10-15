package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.service.PortfolioService;
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

public class PortfolioControllerTest {

    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private PortfolioController portfolioController;

    private Portfolio portfolio;
    private Client client;;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        portfolio = new Portfolio(client);
    }

    @Test
    void testGetAllPortfolios() {
        when(portfolioService.getAllPortfolios()).thenReturn(List.of(portfolio));

        List<Portfolio> found = portfolioController.getAllPortfolios();
        assertThat(found).hasSize(1).contains(portfolio);
    }

    @Test
    void testGetPortfolioById() {
        when(portfolioService.getPortfolioById(1L)).thenReturn(Optional.of(portfolio));

        ResponseEntity<Portfolio> response = portfolioController.getPortfolioById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testCreatePortfolio() {
        when(portfolioService.createPortfolio(any(Portfolio.class))).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.createPortfolio(portfolio);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testUpdatePortfolio() {
        when(portfolioService.updatePortfolio(anyLong(), any(Portfolio.class))).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.updatePortfolio(1L, portfolio);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testDeletePortfolio() {
        ResponseEntity<Void> response = portfolioController.deletePortfolio(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(portfolioService, times(1)).deletePortfolio(1L);
    }
}
