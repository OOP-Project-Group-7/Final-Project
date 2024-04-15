public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private Instructor instructor;
    private Student[] enrolledStudents;
    private int numEnrolled;
    private static final int maxStudents = 30;
    // Store hard coded courses
    public static Course[] courseList = new Course[10]; // Assuming max 10 courses
    private static int numCourses = 0;

    public Course(String courseCode, String courseName, int credits, Instructor instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
        this.enrolledStudents = new Student[maxStudents];
        this.numEnrolled = 0;

        courseList[numCourses++] = this;
    }

    public static void initializePredefinedCourses() {
        Course[] predefinedCourses = {
            new Course("CS101", "Introduction to Computer Science", 3, instructorObject1, maxStudents),
            new Course("ENG101", "Introduction to English Literature", 3, instructorObject2, maxStudents),
            // Add more predefined courses as needed
        };

        for (Course course : predefinedCourses) {
            courseList[numCourses++] = course;
        }
    }

    // Static method to display available courses
    public static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (int i = 0; i < numCourses; i++) {
            System.out.println(courseList[i].getCourseCode() + ": " + courseList[i].getCourseName());
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student[] getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getNumEnrolled() {
        return numEnrolled;
    }

    public void enrollStudent(Student student) {
        if (numEnrolled < maxStudents) {
            enrolledStudents[numEnrolled++] = student;
            student.enrollCourse(this); // Enroll the student in this course
        } else {
            System.out.println("Maximum students enrolled. Cannot enroll more students.");
        }
    }

    public void dropStudent(Student student) {
        for (int i = 0; i < numEnrolled; i++) {
            if (enrolledStudents[i].equals(student)) {
                // Remove the student and shift remaining students to the left
                for (int j = i; j < numEnrolled - 1; j++) {
                    enrolledStudents[j] = enrolledStudents[j + 1];
                }
                enrolledStudents[numEnrolled - 1] = null;
                numEnrolled--;
                student.dropCourse(this);
                return;
            }
        }
        System.out.println("Student not found in the course.");
    }

    public void recordGrade(Student student, Grade grade) {
        // Assuming that the student is already enrolled in the course
        for (int i = 0; i < numEnrolled; i++) {
            if (enrolledStudents[i].equals(student)) {
                // Record the grade for the student
                // You might want to store it in a separate array or data structure
                // For simplicity, let's print it for now
                /* System.out.println("Grade recorded for student " + student.getFirstName() + " " + student.getLastName() +
                        " in course " + name + ": " + grade.getLetterGrade()); */
                System.out.println(student.getFirstName() + student.getLastName() + " scored" + grade.getLetterGrade() + 
                        " in " + courseName);        
                return;
            }
        }
        System.out.println("Student not found in the course.");
    }

    public void displayEnrolledStudents() {
        System.out.println("Enrolled students in " + courseName + ":");
        for (int i = 0; i < numEnrolled; i++) {
            System.out.println(enrolledStudents[i].getFirstName() + " " + enrolledStudents[i].getLastName());
        }
    }
}
