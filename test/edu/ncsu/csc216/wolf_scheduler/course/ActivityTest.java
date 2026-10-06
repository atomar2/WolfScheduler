package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the conflict checking functionality of the Activity class.
 * Verifies that activities with overlapping days and times are
 * identified as conflicts and that non-conflicting activities are allowed.
 *
 * @author Aanya Tomar (atomar3)
 */
class ActivityTest {

    /**
     * Tests checkConflict() when the activities occur at the same time
     * but do not share any meeting days.
     */
    @Test
    void testCheckConflict() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "MW", 1330, 1445);
        Activity a2 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "TH", 1330, 1445);

        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }

    /**
     * Tests checkConflict() when two activities share a meeting day
     * and have overlapping meeting times.
     */
    @Test
    void testCheckConflictWithConflict() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "MW", 1330, 1445);
        Activity a2 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "M", 1330, 1445);

        Exception e1 = assertThrows(ConflictException.class,
                () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());

        Exception e2 = assertThrows(ConflictException.class,
                () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }
    

    /**
     * Tests checkConflict() when one activity ends at the same minute
     * that another activity begins.
     */
    @Test
    void testCheckConflictAtBoundary() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "M", 1330, 1445);

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "M", 1445, 1530);

        Exception e1 = assertThrows(ConflictException.class,
                () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());

        Exception e2 = assertThrows(ConflictException.class,
                () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }
    
    /**
     * Tests checkConflict() when two activities share a meeting day
     * but their meeting times do not overlap.
     */
    @Test
    void testCheckConflictNoTimeOverlap() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "M", 1330, 1445);

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "M", 1500, 1600);

        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }
    
    /**
     * Tests checkConflict() when two activities share a meeting day
     * and their meeting times partially overlap.
     */
    @Test
    void testCheckConflictPartialOverlap() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "MW", 1330, 1445);

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "W", 1400, 1530);

        Exception e1 = assertThrows(ConflictException.class,
                () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());

        Exception e2 = assertThrows(ConflictException.class,
                () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }

    /**
     * Tests checkConflict() when the meeting time of one activity
     * is completely contained within the meeting time of another activity.
     */
    @Test
    void testCheckConflictContained() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "M", 1300, 1600);

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "M", 1400, 1500);

        Exception e1 = assertThrows(ConflictException.class,
                () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());

        Exception e2 = assertThrows(ConflictException.class,
                () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }

    /**
     * Tests checkConflict() when two activities share only one meeting day
     * and have overlapping meeting times on that day.
     */
    @Test
    void testCheckConflictOneCommonDay() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "001", 3, "sesmith5", "MW", 1330, 1445);

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "W", 1400, 1500);

        Exception e = assertThrows(ConflictException.class,
                () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e.getMessage());
    }

    /**
     * Tests checkConflict() when one activity has arranged meeting days.
     * Arranged activities should not conflict with scheduled activities.
     */
    @Test
    void testCheckConflictArranged() {
        Activity a1 = new Course("CSC 216", "Software Development Fundamentals",
                "601", 3, "sesmith5", "A");

        Activity a2 = new Course("CSC 217", "Software Development Fundamentals Lab",
                "001", 2, "sesmith5", "M", 1330, 1445);

        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }
    
}