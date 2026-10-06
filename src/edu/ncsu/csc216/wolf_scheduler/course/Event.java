package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents an Event that can be added to a schedule.
 * An Event contains a title, meeting information, and event details.
 *
 * @author Aanya Tomar (atomar3)
 */
public class Event extends Activity {

    /** Details about the Event. */
    private String eventDetails;

    /**
     * Constructs an Event with the given title, meeting days,
     * start time, end time, and event details.
     *
     * @param title title of the Event
     * @param meetingDays meeting days for the Event
     * @param startTime start time for the Event
     * @param endTime end time for the Event
     * @param eventDetails details about the Event
     */
    public Event(String title, String meetingDays, int startTime,
            int endTime, String eventDetails) {
        super(title, meetingDays, startTime, endTime);
        setEventDetails(eventDetails);
    }

    /**
     * Returns the Event's details.
     *
     * @return the Event details
     */
    public String getEventDetails() {
        return eventDetails;
    }

    /**
     * Sets the Event's details.
     *
     * @param eventDetails the Event details to set
     * @throws IllegalArgumentException if eventDetails is null
     */
    public void setEventDetails(String eventDetails) {
        if (eventDetails == null) {
            throw new IllegalArgumentException("Invalid event details.");
        }
        this.eventDetails = eventDetails;
    }

    /**
     * Returns Event information for the short display.
     * The first two elements are empty because an Event does not
     * have a course name or section.
     *
     * @return String array containing the short display information
     */
    @Override
    public String[] getShortDisplayArray() {
        return new String[] {
            "",
            "",
            getTitle(),
            getMeetingString()
        };
    }

    /**
     * Returns Event information for the long display.
     * The array contains empty course-specific fields followed by
     * the Event title, meeting information, and event details.
     *
     * @return String array containing the long display information
     */
    @Override
    public String[] getLongDisplayArray() {
        return new String[] {
            "",
            "",
            getTitle(),
            "",
            "",
            getMeetingString(),
            eventDetails
        };
    }

    /**
     * Sets the Event's meeting days and times.
     * Event meeting days may contain U, M, T, W, H, F, and S
     * with no duplicate days. Events cannot be arranged.
     *
     * @param meetingDays meeting days for the Event
     * @param startTime start time for the Event
     * @param endTime end time for the Event
     * @throws IllegalArgumentException if the meeting days or times are invalid
     */
    @Override
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

        if (meetingDays == null || "".equals(meetingDays)) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        int countU = 0;
        int countM = 0;
        int countT = 0;
        int countW = 0;
        int countH = 0;
        int countF = 0;
        int countS = 0;

        for (int i = 0; i < meetingDays.length(); i++) {
            char c = meetingDays.charAt(i);

            switch (c) {
            case 'U':
                countU++;
                break;
            case 'M':
                countM++;
                break;
            case 'T':
                countT++;
                break;
            case 'W':
                countW++;
                break;
            case 'H':
                countH++;
                break;
            case 'F':
                countF++;
                break;
            case 'S':
                countS++;
                break;
            default:
                throw new IllegalArgumentException("Invalid meeting days and times.");
            }
        }

        if (countU > 1 || countM > 1 || countT > 1
                || countW > 1 || countH > 1
                || countF > 1 || countS > 1) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }
    
    /**
     * Determines if the given Activity is a duplicate Event.
     * Two Events are duplicates if they have the same title.
     *
     * @param activity Activity to compare
     * @return true if the Activity is a duplicate Event, false otherwise
     */
    @Override
    public boolean isDuplicate(Activity activity) {
        if (activity instanceof Event) {
            Event event = (Event) activity;
            return getTitle().equals(event.getTitle());
        }

        return false;
    }
    
    /**
     * Returns a comma-separated String containing all Event fields.
     * The fields are title, meeting days, start time, end time,
     * and event details.
     *
     * @return String representation of the Event
     */
    @Override
    public String toString() {
        return getTitle() + "," + getMeetingDays() + ","
                + getStartTime() + "," + getEndTime() + ","
                + eventDetails;
    }
}