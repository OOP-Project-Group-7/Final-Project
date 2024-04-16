public class Course {
    private String courseCode;
    private String courseName;
    private double credits;
    private Instructor instructor;
    private Student[] enrolledStudents;
    private int numEnrolled;
    private static final int maxStudents = 30;

    // Store hard coded courses
    public static Course[] courseList = new Course[10]; // Set the Array size to 10
    private static int numCourses = 0;

    public Course(String courseCode, String courseName, double credits, Instructor instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
        this.enrolledStudents = new Student[maxStudents];
        this.numEnrolled = 0;

        courseList[numCourses++] = this;
    }

    public static void initializePredefinedCourses() {
        numCourses = 0; 
    
        // Get array of instructors
        Instructor[] instructors = Instructor.initializePreinitializedInstructors();
    
        // Create array of courses
        Course[] predefinedCourses = {
            new Course("CS111", "Introduction to Computing ", 1, instructors[0]),
            new Course("BUSA161", "FDE I", 1, instructors[1]),
            new Course("MATH111", "Calculus I", 1, instructors[2]),
            new Course("ENGL112", "Written and Oral Communication", 1, instructors[3]),
            new Course("SOAN111", "Leadership 1", 0.5, instructors[4]),        
        };
    
        // Add these courses to the course list
        for (int i=0; i<predefinedCourses.length; i++) {
            courseList[i] = predefinedCourses[i];
        }
    }

    // Method which displays available courses
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

    public double getCredits() {
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

    // Enrolls a student in a course
    public void enrollStudent(Student student) {
        if (numEnrolled < maxStudents) {
            enrolledStudents[numEnrolled++] = student;
            student.enrollCourse(this); 
        } else {
            System.out.println("Maximum students enrolled. Cannot enroll more students.");
        }
    }

    // Removes a student from EnrolledStudents 
    public void dropStudent(Student student) {
        for (int i = 0; i < numEnrolled; i++) {
            if (enrolledStudents[i].equals(student)) {
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
        for (int i = 0; i < numEnrolled; i++) {
            if (enrolledStudents[i].equals(student)) {
                // Record the grade for the student    
                System.out.println("Grade recorded for " + student.getFirstName() + " in " + courseName + ": " + grade.getLetterGrade());
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


