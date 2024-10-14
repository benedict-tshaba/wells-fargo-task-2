package com.wellsfargo.counselor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

    @Test
    void testAdvisorCreation() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        assertNotNull(advisor);
        assertEquals("John", advisor.getFirstName());
        assertEquals("Doe", advisor.getLastName());
    }

    @Test
    void testSetters() {
        Advisor advisor = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        advisor.setFirstName("Jane");
        assertEquals("Jane", advisor.getFirstName());
    }

    @Test
    void testEqualsAndHashCode() {
        Advisor advisor1 = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        Advisor advisor2 = new Advisor("John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        assertEquals(advisor1, advisor2);
        assertEquals(advisor1.hashCode(), advisor2.hashCode());
    }
}
