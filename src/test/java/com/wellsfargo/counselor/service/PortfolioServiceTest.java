package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.repository.PortfolioRepository;
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

public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    private Portfolio portfolio;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        portfolio = new Portfolio(client);
    }

    @Test
    void testGetAllPortfolios() {
        when(portfolioRepository.findAll()).thenReturn(List.of(portfolio));

        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        assertThat(portfolios).hasSize(1).contains(portfolio);
    }

    @Test
    void testGetPortfolioById() {
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));

        Optional<Portfolio> found = portfolioService.getPortfolioById(1L);
        assertThat(found).isPresent().contains(portfolio);
    }

    @Test
    void testCreatePortfolio() {
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(portfolio);

        Portfolio created = portfolioService.createPortfolio(portfolio);
        assertThat(created).isEqualTo(portfolio);
    }

    @Test
    void testUpdatePortfolio() {
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(portfolio);

        Portfolio updated = portfolioService.updatePortfolio(1L, portfolio);
        assertThat(updated).isEqualTo(portfolio);
    }

    @Test
    void testDeletePortfolio() {
        doNothing().when(portfolioRepository).deleteById(1L);
        portfolioService.deletePortfolio(1L);
        verify(portfolioRepository, times(1)).deleteById(1L);
    }
}
