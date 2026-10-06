package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents an activity that can be added to a schedule.
 * Contains the common information and behavior shared by
 * courses and events.
 *
 * @author Aanya Tomar (atomar3)
 */
public abstract class Activity implements Conflict {

    /** Upper possible hour. */
    private static final int UPPER_HOUR = 24;

    /** Upper possible minute. */
    private static final int UPPER_MINUTE = 60;

    /** Activity's title. */
    private String title;

    /** Activity's meeting days. */
    private String meetingDays;

    /** Activity's starting time. */
    private int startTime;

    /** Activity's ending time. */
    private int endTime;

    /**
     * Constructs an Activity with the given title, meeting days,
     * start time, and end time.
     *
     * @param title title of the Activity
     * @param meetingDays meeting days for the Activity
     * @param startTime start time for the Activity
     * @param endTime end time for the Activity
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

    /**
     * Returns the Activity's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Activity's title.
     *
     * @param title the title to set
     * @throws IllegalArgumentException if the title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || "".equals(title)) {
            throw new IllegalArgumentException("Invalid title.");
        }

        this.title = title;
    }

    /**
     * Returns the Activity's meeting days.
     *
     * @return the meeting days
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * Returns the Activity's start time.
     *
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the Activity's end time.
     *
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets the Activity's meeting days and times.
     * Checks that the meeting days are not null or empty and validates
     * the start and end times.
     *
     * @param meetingDays meeting days for the Activity
     * @param startTime start time for the Activity
     * @param endTime end time for the Activity
     * @throws IllegalArgumentException if the meeting days or times are invalid
     */
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

        if (meetingDays == null || "".equals(meetingDays)) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        int startHour = startTime / 100;
        int startMin = startTime % 100;
        int endHour = endTime / 100;
        int endMin = endTime % 100;

        if (startHour < 0 || startHour >= UPPER_HOUR) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        if (startMin < 0 || startMin >= UPPER_MINUTE) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        if (endHour < 0 || endHour >= UPPER_HOUR) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        if (endMin < 0 || endMin >= UPPER_MINUTE) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        if (endTime < startTime) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        this.meetingDays = meetingDays;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Activity's meeting days and times.
     *
     * @return the Activity's meeting days and times
     */
    public String getMeetingString() {
        if ("A".equals(meetingDays)) {
            return "Arranged";
        }

        return meetingDays + " " + getTimeString(startTime)
                + "-" + getTimeString(endTime);
    }

    /**
     * Converts a military time integer to a time string in AM/PM format.
     *
     * @param time time as an integer in military time
     * @return time as a String in AM/PM format
     */
    private String getTimeString(int time) {
        int hour = time / 100;
        int min = time % 100;
        boolean morning = true;

        if (hour >= 12) {
            hour -= 12;
            morning = false;
        }

        if (hour == 0) {
            hour = 12;
        }

        String minS = "" + min;

        if (min < 10) {
            minS = "0" + minS;
        }

        String end = morning ? "AM" : "PM";

        return hour + ":" + minS + end;
    }

    /**
     * Returns an array containing information used for the short
     * display of the Activity.
     *
     * @return String array containing short display information
     */
    public abstract String[] getShortDisplayArray();

    /**
     * Returns an array containing information used for the long
     * display of the Activity.
     *
     * @return String array containing long display information
     */
    public abstract String[] getLongDisplayArray();

    /**
     * Determines if the given Activity is a duplicate of this Activity.
     *
     * @param activity Activity to compare
     * @return true if the Activity is a duplicate, false otherwise
     */
    public abstract boolean isDuplicate(Activity activity);
    
    
    /**
     * Checks whether this Activity conflicts with the given Activity.
     * Activities conflict when they share at least one meeting day and
     * their meeting times overlap. Meeting times that share an endpoint
     * are considered overlapping. Arranged activities do not conflict.
     *
     * @param possibleConflictingActivity Activity to check for a conflict
     * @throws ConflictException if the activities have a scheduling conflict
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity)
            throws ConflictException {

        if ("A".equals(getMeetingDays())
                || "A".equals(possibleConflictingActivity.getMeetingDays())) {
            return;
        }

        for (int i = 0; i < getMeetingDays().length(); i++) {
            char day = getMeetingDays().charAt(i);

            if (possibleConflictingActivity.getMeetingDays().indexOf(day) >= 0) {

                boolean timesOverlap =
                        getStartTime() <= possibleConflictingActivity.getEndTime()
                        && possibleConflictingActivity.getStartTime() <= getEndTime();

                if (timesOverlap) {
                    throw new ConflictException();
                }
            }
        }
    }
    

	/**
     * Generates a hash code for the Activity using all Activity fields.
     *
     * @return hash code for the Activity
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endTime;
        result = prime * result
                + ((meetingDays == null) ? 0 : meetingDays.hashCode());
        result = prime * result + startTime;
        result = prime * result
                + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /**
     * Compares the given object to this Activity for equality.
     *
     * @param obj object to compare to this Activity
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Activity other = (Activity) obj;

        if (endTime != other.endTime)
            return false;

        if (meetingDays == null) {
            if (other.meetingDays != null)
                return false;
        } else if (!meetingDays.equals(other.meetingDays))
            return false;

        if (startTime != other.startTime)
            return false;

        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;

        return true;
    }
}