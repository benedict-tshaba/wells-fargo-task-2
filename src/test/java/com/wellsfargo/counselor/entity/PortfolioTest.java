package com.wellsfargo.counselor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    @Test
    void testPortfolioCreation() {
        Client client = new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678");
        Portfolio portfolio = new Portfolio(client);
        assertNotNull(portfolio);
        assertEquals(client, portfolio.getClient());
    }

    @Test
    void testSetters() {
        Client client = new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678");
        Portfolio portfolio = new Portfolio(client);
        Client newClient = new Client(new Advisor("Jane", "Doe", "456 Elm St", "555-8765", "jane.doe@example.com"), "Bob", "bob@example.com", "555-4321");
        portfolio.setClient(newClient);
        assertEquals(newClient, portfolio.getClient());
    }

    @Test
    void testEqualsAndHashCode() {
        Client client = new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678");
        Portfolio portfolio1 = new Portfolio(client);
        Portfolio portfolio2 = new Portfolio(client);
        assertEquals(portfolio1, portfolio2);
        assertEquals(portfolio1.hashCode(), portfolio2.hashCode());
    }
}
