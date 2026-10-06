package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Defines the behavior for objects that can check for scheduling
 * conflicts with other activities.
 *
 * @author Aanya Tomar (atomar3)
 */
public interface Conflict {

    /**
     * Checks whether the given Activity conflicts with this Activity.
     * Activities conflict when they share at least one meeting day and
     * their meeting times overlap. Meeting times that share an endpoint
     * are considered overlapping.
     *
     * @param possibleConflictingActivity Activity to check for a conflict
     * @throws ConflictException if this Activity conflicts with the
     *         possible conflicting Activity
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}