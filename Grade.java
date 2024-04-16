public class Grade {
    private float score;
    private String letterGrade;
    private double gradePoint;

    public Grade(float score) {
        this.score = score;
        this.letterGrade = convertToLetter(score);
        this.gradePoint = convertToGradePoint(letterGrade);
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
        this.letterGrade = convertToLetter(score);
        this.gradePoint = convertToGradePoint(letterGrade);
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    // Static method to convert a numerical score to a letter grade
    public static String convertToLetter(float score) {
        if (score >= 85) {
            return "A+";
        } else if (score >= 80) {
            return "A";
        } else if (score >= 75) {
            return "B+";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 65) {
            return "C+";
        } else if (score >= 60) {
            return "C";
        } else if (score >= 55) {
            return "D+";
        } else if (score >= 50) {
            return "D";
        } else {
            return "E";
        }
    }
    
    // Static method to convert a letter grade to a grade point value
    public static double convertToGradePoint(String grade) {
        double gradePoint = 0;
        if (grade == "A+") {
            gradePoint = 4.00;
        } else if (grade == "A") {
            gradePoint = 4.00;
        } else if (grade == "B+") {
            gradePoint = 3.50;
        } else if (grade == "B") {
            gradePoint = 3.00;
        } else if (grade == "C+") {
            gradePoint = 2.50;
        } else if (grade == "C") {
            gradePoint = 2.00;
        } else if (grade == "D+") {
            gradePoint = 1.50;
        } else if (grade == "D") {
            gradePoint = 1.00;
        } else if (grade == "E") {
            gradePoint = 0.00;
        }
            return gradePoint;
        
    }
}
