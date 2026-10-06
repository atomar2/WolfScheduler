package edu.ncsu.csc216.wolf_scheduler.scheduler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;

/**
 * Manages a course catalog and a student's schedule.
 * Provides functionality for adding and removing courses,
 * renaming and resetting the schedule, and exporting the schedule.
 *
 * @author Sarah Heckman
 * @author Aanya Tomar (atomar3)
 */
public class WolfScheduler {

	/** Course catalog. */
	private ArrayList<Course> catalog;

	/** Student schedule. */
	private ArrayList<Activity> schedule;

	/** Schedule title. */
	private String title;

	/**
	 * Creates a WolfScheduler using the given course records file.
	 *
	 * @param fileName file containing course records
	 * @throws IllegalArgumentException if the course records file cannot be found
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Adds an Event to the schedule.
	 *
	 * @param eventTitle title of the Event
	 * @param eventMeetingDays meeting days for the Event
	 * @param eventStartTime start time for the Event
	 * @param eventEndTime end time for the Event
	 * @param eventDetails details about the Event
	 * @throws IllegalArgumentException if the Event is a duplicate or conflicts
	 *         with an Activity already in the schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays,
	        int eventStartTime, int eventEndTime, String eventDetails) {

	    Event event = new Event(eventTitle, eventMeetingDays,
	            eventStartTime, eventEndTime, eventDetails);

	    for (Activity activity : schedule) {
	        if (event.isDuplicate(activity)) {
	            throw new IllegalArgumentException(
	                    "You have already created an event called " + eventTitle);
	        }

	        try {
	            event.checkConflict(activity);
	        } catch (ConflictException e) {
	            throw new IllegalArgumentException(
	                    "The event cannot be added due to a conflict.");
	        }
	    }

	    schedule.add(event);
	}
	
	/**
	 * Returns the course catalog as a 2D String array.
	 *
	 * @return course catalog information
	 */
	public String[][] getCourseCatalog() {
	    String[][] catalogArray = new String[catalog.size()][4];

	    for (int i = 0; i < catalog.size(); i++) {
	        Course course = catalog.get(i);
	        catalogArray[i] = course.getShortDisplayArray();
	    }

	    return catalogArray;
	}

	/**
	 * Returns the scheduled Activities as a 2D String array.
	 *
	 * @return scheduled Activity information
	 */
	public String[][] getScheduledActivities() {
	    String[][] scheduleArray = new String[schedule.size()][4];

	    for (int i = 0; i < schedule.size(); i++) {
	        Activity activity = schedule.get(i);
	        scheduleArray[i] = activity.getShortDisplayArray();
	    }

	    return scheduleArray;
	}

	/**
	 * Returns the scheduled Activities as a 2D String array containing
	 * full Activity information.
	 *
	 * @return full scheduled Activity information
	 */
	public String[][] getFullScheduledActivities() {
	    String[][] scheduleArray = new String[schedule.size()][7];

	    for (int i = 0; i < schedule.size(); i++) {
	        Activity activity = schedule.get(i);
	        scheduleArray[i] = activity.getLongDisplayArray();
	    }

	    return scheduleArray;
	}

	/**
	 * Returns the Course in the catalog with the given name and section.
	 *
	 * @param name course name
	 * @param section course section
	 * @return matching Course, or null if no matching Course exists
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (Course course : catalog) {
			if (course.getName().equals(name)
					&& course.getSection().equals(section)) {
				return course;
			}
		}

		return null;
	}

	/**
	 * Adds the Course with the given name and section to the schedule.
	 *
	 * @param name course name
	 * @param section course section
	 * @return true if the Course is added, false if it is not in the catalog
	 * @throws IllegalArgumentException if the Course is a duplicate or conflicts
	 *         with an Activity already in the schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
	    Course course = getCourseFromCatalog(name, section);

	    if (course == null) {
	        return false;
	    }

	    for (Activity activity : schedule) {
	        if (course.isDuplicate(activity)) {
	            throw new IllegalArgumentException(
	                    "You are already enrolled in " + name);
	        }

	        try {
	            course.checkConflict(activity);
	        } catch (ConflictException e) {
	            throw new IllegalArgumentException(
	                    "The course cannot be added due to a conflict.");
	        }
	    }

	    schedule.add(course);
	    return true;
	}
	
	/**
	 * Removes the Activity at the given index from the schedule.
	 *
	 * @param idx index of the Activity to remove
	 * @return true if the Activity is removed, false if the index is invalid
	 */
	public boolean removeActivityFromSchedule(int idx) {
	    try {
	        schedule.remove(idx);
	        return true;
	    } catch (IndexOutOfBoundsException e) {
	        return false;
	    }
	}

	/**
	 * Resets the schedule to an empty schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}

	/**
	 * Returns the schedule title.
	 *
	 * @return schedule title
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Sets the schedule title.
	 *
	 * @param title new schedule title
	 * @throws IllegalArgumentException if title is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}

		this.title = title;
	}

	/**
	 * Exports the schedule to a file.
	 *
	 * @param fileName file to save the schedule to
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}