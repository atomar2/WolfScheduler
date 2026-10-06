package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files and writes Course records to a file.
 * 
 * @author Sarah Heckman
 * @author Aanya Tomar (atomar3)
 */
public class CourseRecordIO {

    /**
     * Constructs a CourseRecordIO.
     */
    public CourseRecordIO() {
        // Default constructor
    }

    /**
     * Reads course records from a file and generates a list of valid Courses.
     * Invalid Course records are ignored.
     *
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static ArrayList<Course> readCourseRecords(String fileName)
            throws FileNotFoundException {

		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();

		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());

				boolean duplicate = false;

				for (Course existingCourse : courses) {
					if (existingCourse.getName().equals(course.getName())
							&& existingCourse.getSection().equals(course.getSection())) {
						duplicate = true;
						break;
					}
				}

				if (!duplicate) {
					courses.add(course);
				}
				
			} catch (IllegalArgumentException e) {
				// Skip invalid course records
			}
		}

		fileReader.close();
		return courses;
	}

	/**
	 * Reads a single Course record from a line of text.
	 *
	 * @param line the line containing Course information
	 * @return a Course created from the line
	 * @throws IllegalArgumentException if the line is malformed or contains invalid Course data
	 */
	private static Course readCourse(String line) {
	    try (Scanner lineScanner = new Scanner(line)) {
	        lineScanner.useDelimiter(",");

	        try {
	            String name = lineScanner.next();
	            String title = lineScanner.next();
	            String section = lineScanner.next();
	            int credits = lineScanner.nextInt();
	            String instructorId = lineScanner.next();
	            String meetingDays = lineScanner.next();

	            if ("A".equals(meetingDays)) {
	                if (lineScanner.hasNext()) {
	                    throw new IllegalArgumentException();
	                }

	                return new Course(name, title, section, credits,
	                        instructorId, meetingDays);
	            }

	            int startTime = lineScanner.nextInt();
	            int endTime = lineScanner.nextInt();

	            if (lineScanner.hasNext()) {
	                throw new IllegalArgumentException();
	            }

	            return new Course(name, title, section, credits,
	                    instructorId, meetingDays, startTime, endTime);

	        } catch (java.util.NoSuchElementException e) {
	            throw new IllegalArgumentException();
	        }
	    }
	}

}
