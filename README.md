WolfScheduler
A Java desktop application for building a student schedule from a course catalog and custom events. WolfScheduler checks for duplicate activities and overlapping meeting times, helping users create a schedule they can review and export.
Developed by Aanya Tomar as part of CSC216: Software Development Fundamentals at North Carolina State University, using course-provided specifications and starter code.
Features
- Load a course catalog from a text file and view course names, sections, titles, and meeting times.
- Add courses and custom events with meeting days, start and end times, and event details.
- Reject duplicate courses, duplicate event titles, and conflicting meeting times.
- Validate course information and activity meeting days and times.
- Remove activities, reset the schedule, and customize its title.
- View a final schedule and export its activity records to a text file.
- Interact with the scheduler through a Java Swing interface.
Technologies and Design
- Java 21
- Java Swing for the desktop interface
- JUnit 5 for unit testing
- Eclipse project configuration
- Javadoc, with Checkstyle and PMD configuration files
The model uses an abstract Activity class with Course and Event subclasses. Shared validation and conflict detection live in the activity model, while subclass methods provide activity-specific behavior. A Conflict interface and ConflictException represent scheduling conflicts. Separate packages handle the model, file input/output, scheduling operations, and user interface.
Project Structure
The uploaded archive contains an Eclipse project in the WolfScheduler/ directory:
| Path | Contents |
| --- | --- |
| `WolfScheduler/src/` | Java source code |
| `WolfScheduler/test/` | JUnit test classes |
| `WolfScheduler/test-files/` | Sample catalogs, invalid input records, and expected output files |
| `WolfScheduler/project_docs/` | System test plan |
| `WolfScheduler/doc/` | Generated Javadoc documentation |
If the Eclipse project contents are placed directly at the repository root, these paths begin with src/, test/, and so on instead.
Getting Started
Requirements
- JDK 21
- Eclipse with Java support and JUnit 5 support
- A desktop environment for the Swing interface
Run in Eclipse
1. Clone or download the repository and extract it if necessary.
2. In Eclipse, select File → Import → General → Existing Projects into Workspace.
3. Select the directory containing the project's .project file and import WolfScheduler.
4. Confirm that the project's JRE System Library uses JavaSE-21.
5. Open src/edu/ncsu/csc216/wolf_scheduler/ui/WolfSchedulerGUI.java and select Run As → Java Application.
6. When the file chooser opens, select test-files/course_records.txt from the Eclipse project directory.
Use the Application
Select a course from the catalog and click Add Course, or enter the details for a custom event and click Add Event. The application reports invalid entries, duplicates, and scheduling conflicts. Use Remove Activity, Reset Schedule, and Set Title to revise the schedule. Click Display Final Schedule to review it, then Export Schedule to save the activity records.
Testing
The repository includes JUnit 5 tests for:
- Course and event construction, validation, and display behavior
- Activity conflict detection and conflict exceptions
- Course record input and activity record output
- Schedule management, duplicate handling, and export behavior
To run the suite in Eclipse, right-click the test directory and select Run As → JUnit Test. Use the Eclipse project directory as the working directory so relative paths to test-files/ resolve correctly.
The manual system test plan is located at WolfScheduler/project_docs/CSC216_GP3_SystemTestPlan.pdf. Generated API documentation can be opened through WolfScheduler/doc/index.html.
Credits
This is an academic guided project completed by Aanya Tomar. Course-provided code includes the Swing GUI authored by Sarah Heckman, along with starter components credited in the source files. Existing source attribution is retained.
