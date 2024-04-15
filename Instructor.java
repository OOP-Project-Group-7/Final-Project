public class Instructor extends Person {

    public Instructor(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void teach(Course course) {
        System.out.println(getFirstName() + " " + getLastName() + " is teaching " + course.getCourseName());
    }
}
