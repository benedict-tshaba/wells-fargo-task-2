package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.service.ClientService;
import com.wellsfargo.counselor.entity.Advisor;
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

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

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
        when(clientService.getAllClients()).thenReturn(List.of(client));

        List<Client> found = clientController.getAllClients();
        assertThat(found).hasSize(1).contains(client);
    }

    @Test
    void testGetClientById() {
        when(clientService.getClientById(1L)).thenReturn(Optional.of(client));

        ResponseEntity<Client> response = clientController.getClientById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(client);
    }

    @Test
    void testCreateClient() {
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        ResponseEntity<Client> response = clientController.createClient(client);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(client);
    }

    @Test
    void testUpdateClient() {
        when(clientService.updateClient(anyLong(), any(Client.class))).thenReturn(client);

        ResponseEntity<Client> response = clientController.updateClient(1L, client);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(client);
    }

    @Test
    void testDeleteClient() {
        ResponseEntity<Void> response = clientController.deleteClient(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(clientService, times(1)).deleteClient(1L);
    }
}
