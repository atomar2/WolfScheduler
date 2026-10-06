package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Handles writing Activity records to a file.
 * Activities may include Courses and Events.
 *
 * @author Aanya Tomar (atomar3)
 */
public class ActivityRecordIO {

    /**
     * Constructs an ActivityRecordIO.
     */
    public ActivityRecordIO() {
        // Default constructor
    }

    /**
     * Writes the given list of Activities to the specified file.
     * Each Activity is written as a separate record using its
     * String representation.
     *
     * @param fileName name of the file to write Activities to
     * @param activities list of Activities to write
     * @throws IOException if the file cannot be written
     */
    public static void writeActivityRecords(String fileName,
            ArrayList<Activity> activities) throws IOException {

        PrintStream fileWriter = new PrintStream(new File(fileName));

        for (Activity a : activities) {
            fileWriter.println(a.toString());
        }

        fileWriter.close();
    }
}