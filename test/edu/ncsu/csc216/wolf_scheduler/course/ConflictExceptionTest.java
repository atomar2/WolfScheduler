package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ConflictException class and verifies that the
 * appropriate exception messages are returned by its constructors.
 *
 * @author Aanya Tomar (atomar3)
 */
class ConflictExceptionTest {

    /**
     * Tests the ConflictException constructor that accepts a custom
     * exception message.
     */
    @Test
    void testConflictExceptionString() {
        ConflictException ce = new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /**
     * Tests the ConflictException constructor that uses the default
     * exception message.
     */
    @Test
    void testConflictException() {
        ConflictException ce = new ConflictException();
        assertEquals("Schedule conflict.", ce.getMessage());
    }

}