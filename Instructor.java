public class Instructor extends Person {

    public Instructor(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void teach(Course course) {
        System.out.println(getFirstName() + " " + getLastName() + " is teaching " + course.getCourseName());
    }

    // A method to initialize an arrray with instructors
    public static Instructor[] initializePreinitializedInstructors() {
        Instructor[] instructors = {
            new Instructor("Gideon", "Bonsu", "gid.bon@example.com"),
            new Instructor("Elijah", "Boateng", "eli.boat@example.com"),
            new Instructor("Kwame", "Mensah", "k.mens@example.com"),
            new Instructor("David", "Yamoah", "da.yamoah@example.com"),
            new Instructor("Yaw", "Oppong", "y.oppong@example.com"),
        };
        return instructors;
    }

}
