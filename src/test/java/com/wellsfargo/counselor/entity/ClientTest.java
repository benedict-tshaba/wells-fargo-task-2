package com.wellsfargo.counselor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientCreation() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        Client client = new Client(advisor, "Alice", "alice@example.com", "555-5678");
        assertNotNull(client);
        assertEquals("Alice", client.getName());
        assertEquals(advisor, client.getAdvisor());
    }

    @Test
    void testSetters() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        Client client = new Client(advisor, "Alice", "alice@example.com", "555-5678");
        client.setName("Bob");
        assertEquals("Bob", client.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        Client client1 = new Client(advisor, "Alice", "alice@example.com", "555-5678");
        Client client2 = new Client(advisor, "Alice", "alice@example.com", "555-5678");
        assertEquals(client1, client2);
        assertEquals(client1.hashCode(), client2.hashCode());
    }
}
