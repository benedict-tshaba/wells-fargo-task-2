package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.repository.ClientRepository;
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

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Advisor advisor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        advisor = new Advisor("John", "Doe", "123 Main St", "123-456-7890", "john.doe@example.com");
        client = new Client(advisor, "Jane Doe", "jane.doe@example.com", "987-654-3210");
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> clients = clientService.getAllClients();
        assertThat(clients).hasSize(1).contains(client);
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> found = clientService.getClientById(1L);
        assertThat(found).isPresent().contains(client);
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client created = clientService.createClient(client);
        assertThat(created).isEqualTo(client);
    }

    @Test
    void testUpdateClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client updated = clientService.updateClient(1L, client);
        assertThat(updated).isEqualTo(client);
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById(1L);
        clientService.deleteClient(1L);
        verify(clientRepository, times(1)).deleteById(1L);
    }
}
