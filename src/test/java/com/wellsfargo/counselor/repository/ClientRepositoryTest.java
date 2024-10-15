package com.wellsfargo.counselor.repository;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    private Client client;
    private Advisor advisor;

    @BeforeEach
    void setUp() {
        advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        advisorRepository.save(advisor);
        client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        clientRepository.save(client);
    }

    @Test
    void testFindByEmail() {
        Optional<Client> found = clientRepository.findByEmail(client.getEmail());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(client.getName());
    }

    @Test
    void testFindById() {
        Optional<Client> found = clientRepository.findById(client.getClientId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(client.getName());
    }

    @Test
    void testDelete() {
        clientRepository.delete(client);
        Optional<Client> found = clientRepository.findById(client.getClientId());
        assertThat(found).isNotPresent();
    }
}
