package com.wellsfargo.counselor.repository;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PortfolioRepositoryTest {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    private Portfolio portfolio;
    private Client client;

    @BeforeEach
    void setUp() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        advisorRepository.save(advisor);
        client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        clientRepository.save(client);
        portfolio = new Portfolio(client);
        portfolioRepository.save(portfolio);
    }

    @Test
    void testFindById() {
        Optional<Portfolio> found = portfolioRepository.findById(portfolio.getPortfolioId());
        assertThat(found).isPresent();
        assertThat(found.get().getClient().getName()).isEqualTo(client.getName());
    }

    @Test
    void testDelete() {
        portfolioRepository.delete(portfolio);
        Optional<Portfolio> found = portfolioRepository.findById(portfolio.getPortfolioId());
        assertThat(found).isNotPresent();
    }
}
