package com.wellsfargo.counselor.repository;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.entity.Security;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SecurityRepositoryTest {

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    private Security security;
    private Portfolio portfolio;

    @BeforeEach
    void setUp() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        advisorRepository.save(advisor);

        Client client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        clientRepository.save(client);

        portfolio = new Portfolio(client);
        portfolioRepository.save(portfolio);

        security = new Security(portfolio, "AAPL", "Stock", "2023-01-01", 150.00, 10);
        securityRepository.save(security);
    }

    @Test
    void testFindById() {
        Optional<Security> found = securityRepository.findById(security.getSecurityId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(security.getName());
    }

    @Test
    void testDelete() {
        securityRepository.delete(security);
        Optional<Security> found = securityRepository.findById(security.getSecurityId());
        assertThat(found).isNotPresent();
    }
}
