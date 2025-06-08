import java.time.LocalDate;

public class TaskEntry {
    private String task;
    private LocalDate date;

    public TaskEntry(String task) {
        this.task = task;
        this.date = LocalDate.now();
    }

    public String toString() {
        return date + " - " + task;
    }
}
