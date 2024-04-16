import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Calls the methods to initilaize the instructors and courses
        Instructor.initializePreinitializedInstructors();
        Course.initializePredefinedCourses();

        // Prompt user to enter their ID number and name
        System.out.println("\nWelcome to the Ashesi University Academic Management System");
        System.out.print("Enter your student ID number: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: " + firstName.toLowerCase() + "." + lastName.toLowerCase() + "@ashesi.edu.gh"); //email is just first and last name concat with @ashesi.edu.gh

        // Create the student object
        Student student = new Student(studentID, firstName, lastName);

        int choice = 0;
        while (choice != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Enroll in a Course");
            System.out.println("2. Drop a Course");
            System.out.println("3. Record Grade");
            System.out.println("4. Generate Transcript");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enrollInCourse(scanner, student);
                    break;
                case 2:
                    dropCourse(scanner, student);
                    break;
                case 3:
                    recordGrade(scanner, student);
                    break;
                case 4:
                    generateTranscript(student);
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
        scanner.close();
    }

    // Method to Enroll the Student in a course
    private static void enrollInCourse(Scanner scanner, Student student) {
        int coursesEnrolled = 0;
        while (coursesEnrolled < 5) {
            System.out.println("\nSelect a course to enroll in or type 'done' to finish:");
            Course.displayAvailableCourses(); // Display all the available courses that was initialized

            System.out.print("Enter the course code to enroll or 'done' to finish: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            boolean enrolled = false;
            for (Course course : Course.courseList) {
                if (course != null && course.getCourseCode().equals(input)) {
                    course.enrollStudent(student);
                    enrolled = true;
                    coursesEnrolled++;
                    System.out.println("Enrolled in " + course.getCourseName());
                    break;
                }
            }
            if (!enrolled) {
                System.out.println("Course code not found. Please try again.");
            }
        }
        System.out.println("Enrollment completed. Total courses enrolled: " + coursesEnrolled);
    }

    // Method to Drop thre Student from a course they enrolled in
    private static void dropCourse(Scanner scanner, Student student) {
        // Display only the courses the student is enrolled in
        System.out.println("Courses you are enrolled in:");
        displayEnrolledCourses(student);
    
        System.out.print("Enter the course code to drop, or type 'done' to finish: ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("done")) {
            return;
        }
    
        // Attempt to drop the course
        boolean dropped = false;
        for (Course course : Course.courseList) {
            if (course != null && course.getCourseCode().equalsIgnoreCase(input) && isStudentEnrolledInCourse(student, course)) {
                course.dropStudent(student);
                dropped = true;
                System.out.println("Dropped from " + course.getCourseName());
                break;
            }
        }
        if (!dropped) {
            System.out.println("Course code not found or not enrolled in this course. Please try again.");
        }
    }
    
    // Method that prints the courses a student is enrolled in
    private static void displayEnrolledCourses(Student student) {
        boolean found = false;
        for (Course course : Course.courseList) {
            if (course != null && isStudentEnrolledInCourse(student, course)) {
                System.out.println(course.getCourseCode() + ": " + course.getCourseName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("You are not enrolled in any courses.");
        }
    }
    
    // Method that checks whether a student is enrolled in a course
    private static boolean isStudentEnrolledInCourse(Student student, Course course) {
        for (Student s : course.getEnrolledStudents()) {
            if (s != null && s.equals(student)) {
                return true;
            }
        }
        return false;
    }
    

    // Method to record the grade for a student in a course
    public static void recordGrade(Scanner scanner, Student student) {
        System.out.println("Select a course to record a grade for:");
        displayEnrolledCourses(student);
    
        System.out.print("Enter the course code for which to record the grade: ");
        String courseCode = scanner.nextLine().trim();
        float score;
        while (true) {
        System.out.print("Enter the numeric score (0-100): ");
        if (scanner.hasNextFloat()) {
            score = scanner.nextFloat();
            if (score >= 0 && score <= 100) {
                scanner.nextLine(); 
                break;
            }
        } else {
            scanner.nextLine(); 
        }
        System.out.println("Invalid score. Please enter a value between 0 and 100.");
    }
        boolean gradeRecorded = false;
        for (Course course : student.getCoursesEnrolled()) {
            if (course != null && course.getCourseCode().equalsIgnoreCase(courseCode)) {
                String letterGrade = Grade.convertToLetter(score);
                student.addGrade(letterGrade, course);
                gradeRecorded = true;
                System.out.println("Grade recorded for " + student.getFirstName() + " in " + course.getCourseName() + ": " + letterGrade);
                break;
            }
        }
    
        if (!gradeRecorded) {
            System.out.println("Course code not found or not enrolled. Please try again.");
        }
    }
    
    // Method to display the student's transcript - this has course code, course names, grades, credits and the GPA of the Student. 
    public static void generateTranscript(Student student) {
        System.out.println("Transcript for: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(String.format("%-12s %-30s %-7s %-7s", "Course Code", "Course Name", "Grade", "Credits"));
        System.out.println("------------------------------------------------------------------------------");
    
        Course[] courses = student.getCoursesEnrolled();
        String[] grades = student.getGrades();

        Transcript transcript = student.getTranscript();
    
        for (int i = 0; i < student.getNumCourses(); i++) {
            if (courses[i] != null && grades[i] != null) {
                System.out.println(String.format("%-12s %-30s %-7s %-7s", 
                                                 courses[i].getCourseCode(), 
                                                 courses[i].getCourseName(), 
                                                 grades[i],
                                                 courses[i].getCredits()));
            }
        }
    
        System.out.println("-----------------------------------------------------------------------------");
        // Display GPA
        double gpa = transcript.calculateGPA();
        System.out.printf("GPA: %.2f\n", gpa);
        System.out.println("-----------------------------------------------------------------------------");
    }
}
