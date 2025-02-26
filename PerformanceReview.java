package models;
import java.util.Date;

public class PerformanceReview {
    private int employeeId;
    private String review;
    private Date date;
    private int rating; // 1-5 scale

    public PerformanceReview(int employeeId, String review, int rating) {
        this.employeeId = employeeId;
        this.review = review;
        this.rating = rating;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Review for Employee ID " + employeeId + ": " + review + " | Rating: " + rating + "/5 | Date: " + date;
    }
}
