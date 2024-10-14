package com.wellsfargo.counselor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {

    @Test
    void testSecurityCreation() {
        Portfolio portfolio = new Portfolio(new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678"));
        Security security = new Security(portfolio, "AAPL", "Stock", "2021-01-01", 150.00, 10);
        assertNotNull(security);
        assertEquals("AAPL", security.getName());
        assertEquals(portfolio, security.getPortfolio());
    }

    @Test
    void testSetters() {
        Portfolio portfolio = new Portfolio(new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678"));
        Security security = new Security(portfolio, "AAPL", "Stock", "2021-01-01", 150.00, 10);
        security.setName("GOOGL");
        assertEquals("GOOGL", security.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Portfolio portfolio = new Portfolio(new Client(new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"), "Alice", "alice@example.com", "555-5678"));
        Security security1 = new Security(portfolio, "AAPL", "Stock", "2021-01-01", 150.00, 10);
        Security security2 = new Security(portfolio, "AAPL", "Stock", "2021-01-01", 150.00, 10);
        assertEquals(security1, security2);
        assertEquals(security1.hashCode(), security2.hashCode());
    }
}
