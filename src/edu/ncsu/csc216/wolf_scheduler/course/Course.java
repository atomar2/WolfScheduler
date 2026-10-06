package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates a Course object and checks that all fields are valid.
 * A Course is an Activity that contains course-specific information
 * such as a name, section, credits, and instructor.
 *
 * @author Aanya Tomar (atomar3)
 */
public class Course extends Activity {

    /** Minimum length of the Course name. */
    private static final int MIN_NAME_LENGTH = 5;

    /** Maximum length of the Course name. */
    private static final int MAX_NAME_LENGTH = 8;

    /** Minimum number of letters in the Course name. */
    private static final int MIN_LETTER_COUNT = 1;

    /** Maximum number of letters in the Course name. */
    private static final int MAX_LETTER_COUNT = 4;

    /** Required number of digits in the Course name. */
    private static final int DIGIT_COUNT = 3;

    /** Required length of the Course section. */
    private static final int SECTION_LENGTH = 3;

    /** Maximum number of possible credits for a Course. */
    private static final int MAX_CREDITS = 5;

    /** Minimum number of possible credits for a Course. */
    private static final int MIN_CREDITS = 1;

    /** Course's name. */
    private String name;

    /** Course's section. */
    private String section;

    /** Course's credit hours. */
    private int credits;

    /** Course's instructor. */
    private String instructorId;

    /**
     * Constructs a Course object with values for all fields.
     *
     * @param name name of Course
     * @param title title of Course
     * @param section section of Course
     * @param credits credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays meeting days for Course as a series of characters
     * @param startTime start time for Course
     * @param endTime end time for Course
     */
    public Course(String name, String title, String section, int credits,
            String instructorId, String meetingDays, int startTime,
            int endTime) {

        super(title, meetingDays, startTime, endTime);

        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
    }

    /**
     * Constructs a Course with the given name, title, section, credits,
     * instructor id, and meeting days for an arranged Course.
     *
     * @param name name of Course
     * @param title title of Course
     * @param section section of Course
     * @param credits credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays meeting days for Course as a series of characters
     */
    public Course(String name, String title, String section, int credits,
            String instructorId, String meetingDays) {

        this(name, title, section, credits, instructorId, meetingDays, 0, 0);
    }

    /**
     * Returns the Course's name.
     *
     * @return the Course name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Course's name.
     *
     * @param name the Course name to set
     * @throws IllegalArgumentException if the Course name is invalid
     */
    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        if (name.length() < MIN_NAME_LENGTH
                || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        int letterCount = 0;
        int digitCount = 0;
        boolean spaceFound = false;

        for (int i = 0; i < name.length(); i++) {
            if (!spaceFound) {
                if (Character.isLetter(name.charAt(i))) {
                    letterCount++;
                } else if (name.charAt(i) == ' ') {
                    spaceFound = true;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            } else {
                if (Character.isDigit(name.charAt(i))) {
                    digitCount++;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            }
        }

        if (letterCount < MIN_LETTER_COUNT
                || letterCount > MAX_LETTER_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        if (digitCount != DIGIT_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        this.name = name;
    }

    /**
     * Returns the Course's section.
     *
     * @return the Course section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets the Course's section.
     *
     * @param section the section to set
     * @throws IllegalArgumentException if the section is invalid
     */
    public void setSection(String section) {
        if (section == null || section.length() != SECTION_LENGTH) {
            throw new IllegalArgumentException("Invalid section.");
        }

        for (int i = 0; i < section.length(); i++) {
            if (!Character.isDigit(section.charAt(i))) {
                throw new IllegalArgumentException("Invalid section.");
            }
        }

        this.section = section;
    }

    /**
     * Returns the Course's credits.
     *
     * @return the number of Course credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the Course's credits.
     *
     * @param credits the number of credits to set
     * @throws IllegalArgumentException if the number of credits is invalid
     */
    public void setCredits(int credits) {
        if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
            throw new IllegalArgumentException("Invalid credits.");
        }

        this.credits = credits;
    }

    /**
     * Returns the Course's instructor id.
     *
     * @return the instructor id
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Sets the Course's instructor id.
     *
     * @param instructorId the instructor id to set
     * @throws IllegalArgumentException if the instructor id is null or empty
     */
    public void setInstructorId(String instructorId) {
        if (instructorId == null || "".equals(instructorId)) {
            throw new IllegalArgumentException("Invalid instructor id.");
        }

        this.instructorId = instructorId;
    }

    /**
     * Returns a comma-separated String containing all Course fields.
     *
     * @return String representation of the Course
     */
    @Override
    public String toString() {
        if ("A".equals(getMeetingDays())) {
            return name + "," + getTitle() + "," + section + "," + credits
                    + "," + instructorId + "," + getMeetingDays();
        }

        return name + "," + getTitle() + "," + section + "," + credits
                + "," + instructorId + "," + getMeetingDays() + ","
                + getStartTime() + "," + getEndTime();
    }

    /**
     * Returns Course information for the short display.
     * The array contains the Course name, section, title, and meeting string.
     *
     * @return String array containing the short display information
     */
    @Override
    public String[] getShortDisplayArray() {
        return new String[] {
            name,
            section,
            getTitle(),
            getMeetingString()
        };
    }

    /**
     * Returns Course information for the long display.
     * The array contains the Course name, section, title, credits,
     * instructor id, meeting string, and an empty String.
     *
     * @return String array containing the long display information
     */
    @Override
    public String[] getLongDisplayArray() {
        return new String[] {
            name,
            section,
            getTitle(),
            Integer.toString(credits),
            instructorId,
            getMeetingString(),
            ""
        };
    }
    
    /**
     * Sets the Course's meeting days and times.
     * Course meeting days may contain M, T, W, H, and F with no duplicates.
     * An arranged Course uses "A" with start and end times of 0.
     *
     * @param meetingDays meeting days for the Course
     * @param startTime start time for the Course
     * @param endTime end time for the Course
     * @throws IllegalArgumentException if the meeting days or times are invalid
     */
    @Override
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

        if (meetingDays == null || "".equals(meetingDays)) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        if ("A".equals(meetingDays)) {
            if (startTime != 0 || endTime != 0) {
                throw new IllegalArgumentException("Invalid meeting days and times.");
            }

            super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
            return;
        }

        int countM = 0;
        int countT = 0;
        int countW = 0;
        int countH = 0;
        int countF = 0;

        for (int i = 0; i < meetingDays.length(); i++) {
            char c = meetingDays.charAt(i);

            switch (c) {
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
            default:
                throw new IllegalArgumentException("Invalid meeting days and times.");
            }
        }

        if (countM > 1 || countT > 1 || countW > 1
                || countH > 1 || countF > 1) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }

        super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

    
    /**
     * Determines if the given Activity is a duplicate Course.
     * Two Courses are duplicates if they have the same name.
     *
     * @param activity Activity to compare
     * @return true if the Activity is a duplicate Course, false otherwise
     */
    @Override
    public boolean isDuplicate(Activity activity) {
        if (activity instanceof Course) {
            Course course = (Course) activity;
            return name.equals(course.getName());
        }

        return false;
    }
    
    
    /**
     * Generates a hash code for the Course using the Activity fields
     * and all Course-specific fields.
     *
     * @return hash code for the Course
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + credits;
        result = prime * result
                + ((instructorId == null) ? 0 : instructorId.hashCode());
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((section == null) ? 0 : section.hashCode());
        return result;
    }

    /**
     * Compares the given object to this Course for equality.
     * Two Courses are equal when their Activity fields and
     * Course-specific fields are equal.
     *
     * @param obj object to compare to this Course
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!super.equals(obj))
            return false;

        if (getClass() != obj.getClass())
            return false;

        Course other = (Course) obj;

        if (credits != other.credits)
            return false;

        if (instructorId == null) {
            if (other.instructorId != null)
                return false;
        } else if (!instructorId.equals(other.instructorId))
            return false;

        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;

        return true;
    }
}