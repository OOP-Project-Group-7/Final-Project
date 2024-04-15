public class Student extends Person {
    private String studentID;
    private Course[] coursesEnrolled;
    private int numCourses;
    protected static final int maxCourses = 5; // Assuming max 5 courses

    public Student(String studentID, String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        this.studentID = studentID;
        this.coursesEnrolled = new Course[maxCourses]; 
        this.numCourses = 0;
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

    public Transcript getTranscript() {
        return new Transcript(this);
    }
}