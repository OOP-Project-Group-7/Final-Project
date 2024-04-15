public class Grade {
    private float score;
    private String letterGrade;
    private double gradePoint;

    public Grade(float score) {
        this.score = score;
        this.letterGrade = convertToLetter(score);
        this.gradePoint = convertToGradePoint(score);
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
        this.letterGrade = convertToLetter(score);
        this.gradePoint = convertToGradePoint(score);
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
    

    public static double convertToGradePoint(float score) {
        if (score >= 85) {
            return 4.00;
        } else if (score >= 80) {
            return 4.00;
        } else if (score >= 75) {
            return 3.50;
        } else if (score >= 70) {
            return 3.00;
        } else if (score >= 65) {
            return 2.50;
        } else if (score >= 60) {
            return 2.00;
        } else if (score >= 55) {
            return 1.50;
        } else if (score >= 50) {
            return 1.00;
        } else {
            return 0.00;
        }
    }
}
