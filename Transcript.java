public class Transcript {
    private Student student;
    private Course[] courses;
    private String[] letterGrades;
    private double[] gradePoints;
    private int numCourses;

    public Transcript(Student student) {
        this.student = student;
        this.courses = new Course[Student.maxCourses];
        this.letterGrades = new String[Student.maxCourses];
        this.gradePoints = new double[Student.maxCourses];
        this.numCourses = 0;
    }

    public Student getStudent() {
        return student;
    }

    public Course[] getCourses() {
        return courses;
    }

    public String[] getLetterGrades() {
        return letterGrades;
    }

    public double[] getGradePoints() {
        return gradePoints;
    }

    public int getNumCourses() {
        return numCourses;
    }

    public void addCourseGrade(Course course, float score) {
        if (numCourses < courses.length && numCourses < letterGrades.length && numCourses < gradePoints.length) {
            courses[numCourses] = course;
            letterGrades[numCourses] = Grade.convertToLetter(score); // Assign letter grade to the array
            gradePoints[numCourses] = Grade.convertToGradePoint(score);
            numCourses++;
        } else {
            System.out.println("Maximum courses reached in transcript. Cannot add more grades.");
        }
    }  
    

    public double calculateGPA() {
        double totalCredits = 0;
        double totalGradePoints = 0;

        for (int i = 0; i < numCourses; i++) {
            totalCredits += courses[i].getCredits();
            totalGradePoints += gradePoints[i] * courses[i].getCredits();
        }

        // Calculate GPA
        double gpa = totalGradePoints / totalCredits;

        // Round GPA to two decimal places
        gpa = Math.round(gpa * 100.0) / 100.0;

        return gpa;
    }

    public void displayTranscript() {
        System.out.println("Transcript for: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Course\t\tGrade\t\tGrade Point");

        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i].getCourseCode() + "\t\t" + letterGrades[i] + "\t\t" + gradePoints[i]);
        }

        // Format GPA to two decimal places
        String formattedGPA = String.format("%.2f", calculateGPA());
        System.out.println("GPA: " + formattedGPA);
    }
}