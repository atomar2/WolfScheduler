package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents an exception that is thrown when two activities
 * have a scheduling conflict.
 *
 * @author Aanya Tomar (atomar3)
 */
public class ConflictException extends Exception {

    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a ConflictException with the specified exception message.
     *
     * @param message message describing the scheduling conflict
     */
    public ConflictException(String message) {
        super(message);
    }

    /**
     * Constructs a ConflictException with the default message
     * "Schedule conflict."
     */
    public ConflictException() {
        this("Schedule conflict.");
    }

}