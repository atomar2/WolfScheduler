# WolfScheduler

A Java desktop application for building student schedules from a course catalog and custom events. WolfScheduler detects duplicate activities and overlapping meeting times, helping users create schedules they can review and export.

Developed by **Aanya Tomar** as part of **CSC216: Software Development Fundamentals at North Carolina State University**, using course-provided specifications and starter code.

## Features

- Load a course catalog from a text file.
- Browse course names, sections, titles, and meeting times.
- Add courses and custom events to a schedule.
- Create events with meeting days, start and end times, and additional details.
- Detect scheduling conflicts and reject duplicate activities.
- Validate course information and activity meeting days and times.
- Remove activities, reset the schedule, and customize its title.
- Review the final schedule and export activity records to a text file.
- Manage schedules through a Java Swing interface.

## Technologies

- **Java 21**
- **Java Swing**
- **JUnit 5**
- **Eclipse**
- **Javadoc**
- **Checkstyle and PMD configuration**

## Design

WolfScheduler separates activity modeling, scheduling operations, file input/output, and the user interface into distinct packages.

- **Activity:** Abstract superclass containing shared activity information, validation, and conflict detection.
- **Course:** Represents a course with a name, section, credits, instructor, and meeting schedule.
- **Event:** Represents a custom event with a title, meeting schedule, and details.
- **Conflict:** Interface defining conflict-checking behavior.
- **ConflictException:** Custom exception representing overlapping activities.
- **WolfScheduler:** Manages the course catalog, scheduled activities, schedule title, and exports.
- **CourseRecordIO and ActivityRecordIO:** Handle reading course records and writing scheduled activities.
- **WolfSchedulerGUI:** Provides the Swing interface.

The project demonstrates inheritance, polymorphism, interfaces, exception handling, collections, file processing, and unit testing.

## Project Structure

Paths below are relative to the Eclipse project directory, which contains the `.project` file.

| Directory | Contents |
| --- | --- |
| `src/` | Java source code |
| `test/` | JUnit test classes |
| `test-files/` | Sample catalogs, invalid inputs, and expected outputs |
| `project_docs/` | Manual system test plan |
| `doc/` | Generated Javadoc documentation |

## Getting Started

### Requirements

- JDK 21
- Eclipse with Java and JUnit 5 support
- A desktop environment for the Swing interface

### Running the Application

1. Clone or download the repository.
2. Extract the downloaded archive if necessary.
3. In Eclipse, select **File → Import → General → Existing Projects into Workspace**.
4. Select the directory containing the `.project` file and import the project.
5. Confirm that the project's JRE System Library uses **JavaSE-21**.
6. Open `src/edu/ncsu/csc216/wolf_scheduler/ui/WolfSchedulerGUI.java`.
7. Select **Run As → Java Application**.
8. When prompted, select `test-files/course_records.txt` from the project directory.

## Usage

1. Select a course from the catalog and click **Add Course**.
2. To add a custom event, enter its details and click **Add Event**.
3. Resolve any reported invalid entries, duplicates, or meeting-time conflicts.
4. Use **Remove Activity**, **Reset Schedule**, or **Set Title** to revise the schedule.
5. Click **Display Final Schedule** to review scheduled activities.
6. Click **Export Schedule** to save activity records to a text file.

## Testing

The repository includes JUnit 5 tests covering:

- Course and event construction and validation
- Activity display behavior
- Meeting-time conflict detection
- Conflict exceptions
- Course record input and activity record output
- Schedule management and duplicate handling
- Schedule export behavior

To run the tests in Eclipse:

1. Right-click the `test` directory.
2. Select **Run As → JUnit Test**.
3. Use the Eclipse project directory as the working directory so paths to `test-files/` resolve correctly.

The manual system test plan is available at:

`project_docs/CSC216_GP3_SystemTestPlan.pdf`

Generated API documentation is available through:

`doc/index.html`

## Credits

This academic guided project was completed by **Aanya Tomar** using NC State CSC216 specifications and starter code.

Course-provided components include the Java Swing GUI authored by **Sarah Heckman**. Additional starter-code attribution is retained in the source files.
