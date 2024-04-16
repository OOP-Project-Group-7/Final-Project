public class Student extends Person {
    private String studentID;
    private Course[] coursesEnrolled;
    protected int enrolledCount;         // To keep track of how many courses have been enrolled
    private int numCourses;
    protected String[] grades;
    protected Transcript transcript;
    protected static final int maxCourses = 5; // Assuming max 5 courses

    public Student(String studentID, String firstName, String lastName) {
        super(firstName, lastName, firstName.toLowerCase() + "." + lastName.toLowerCase() + "@ashesi.edu.gh");
        this.studentID = studentID;
        this.coursesEnrolled = new Course[maxCourses]; 
        this.numCourses = 0;
        this.transcript = new Transcript(this);
        this.grades = new String[maxCourses];
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Course[] getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void setCoursesEnrolled(Course[] coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public int getNumCourses() {
        return numCourses;
    }

    public void setNumCourses(int numCourses) {
        this.numCourses = numCourses;
    }

    public int getMaxCourses() {
        return maxCourses;
    }

    public void enrollCourse(Course course) {
        if (numCourses < maxCourses) {
            coursesEnrolled[numCourses++] = course;
        } else {
            System.out.println("Maximum courses enrolled. Cannot enroll in more courses.");
        }
        enrolledCount++;
    }

    public void dropCourse(Course course) {
        for (int i = 0; i < numCourses; i++) {
            if (coursesEnrolled[i].equals(course)) {
                // Remove the course and shift remaining courses to the left
                for (int j = i; j < numCourses - 1; j++) {
                    coursesEnrolled[j] = coursesEnrolled[j + 1];
                }
                coursesEnrolled[numCourses - 1] = null;
                numCourses--;
                return;
            }
        }
        System.out.println("Course not found in the student's enrolled courses.");
    }

    public String[] getGrades() {
        return grades;
    }

    public Transcript getTranscript() {
        return new Transcript(this);
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void addGrade(String letterGrade, Course course) {
        // Find the course in the array and add the grade at the same index.
        for (int i = 0; i < numCourses; i++) {
            if (coursesEnrolled[i].equals(course)) {
                grades[i] = letterGrade;
                return;
            }
        }
        System.out.println("Course not found in the student's enrolled courses.");
    }
}