public class Transcript {
    private Student student;
    private Course[] courses;
    private String[] letterGrades;
    private double[] gradePoints;
    protected int numCourses;

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
        String letterGrade = Grade.convertToLetter(score);
        if (numCourses < courses.length) {
            courses[numCourses] = course;
            letterGrades[numCourses] = Grade.convertToLetter(score);
            gradePoints[numCourses] = Grade.convertToGradePoint(letterGrade);
            numCourses++;
        } else {
            System.out.println("Maximum courses reached in transcript. Cannot add more grades.");
        }
    }
    
    
    public String[] getFormattedGrades() {
        String[] formattedGrades = new String[numCourses];
        for (int i = 0; i < numCourses; i++) {
            formattedGrades[i] = String.format("%s\t\t%s\t\t%.2f", 
                                               courses[i].getCourseCode(), 
                                               letterGrades[i], 
                                               gradePoints[i]);
        }
        return formattedGrades;
    }

    public double calculateGPA() {
        double totalCredits = 0;
        double totalGradePoints = 0;
        String[] grades = student.grades;


        for (int i = 0; i < student.getNumCourses(); i++) {
            totalCredits += student.getCoursesEnrolled()[i].getCredits();
            double gradePoint = Grade.convertToGradePoint(grades[i]) ;
            totalGradePoints += gradePoint;
        }

        // Calculate GPA
        double gpa = totalGradePoints / totalCredits;

        // Round GPA to two decimal places
        gpa = Math.round(gpa * 100.0) /100.0;

        return gpa;
    }

    public void displayTranscript() {
        System.out.println("Transcript for: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("----------------------------------------------------");
        System.out.println("Course\t\tGrade\t\tGrade Point");

        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i].getCourseCode() + "\t\t" + letterGrades[i] + "\t\t" + gradePoints[i]);
        }

        // Format GPA to two decimal places
        String formattedGPA = String.format("%.2f", calculateGPA());
        System.out.println("GPA: " + formattedGPA);
    }
}