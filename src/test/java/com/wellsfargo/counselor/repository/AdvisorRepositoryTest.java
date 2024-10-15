package com.wellsfargo.counselor.repository;

import com.wellsfargo.counselor.entity.Advisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AdvisorRepositoryTest {

    @Autowired
    private AdvisorRepository advisorRepository;

    private Advisor advisor;

    @BeforeEach
    void setUp() {
        advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        advisorRepository.save(advisor);
    }

    @Test
    void testFindByEmail() {
        Optional<Advisor> found = advisorRepository.findByEmail(advisor.getEmail());
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo(advisor.getFirstName());
    }

    @Test
    void testFindById() {
        Optional<Advisor> found = advisorRepository.findById(advisor.getAdvisorId());
        assertThat(found).isPresent();
        assertThat(found.get().getLastName()).isEqualTo(advisor.getLastName());
    }

    @Test
    void testDelete() {
        advisorRepository.delete(advisor);
        Optional<Advisor> found = advisorRepository.findById(advisor.getAdvisorId());
        assertThat(found).isNotPresent();
    }
}
