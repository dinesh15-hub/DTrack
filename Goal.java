public class Goal {
    private String goalText;
    private boolean isCompleted;

    public Goal(String goalText) {
        this.goalText = goalText;
        this.isCompleted = false;
    }

    public Goal(String goalText, boolean isCompleted) {
        this.goalText = goalText;
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return (isCompleted ? "[✅]" : "[ ]") + " " + goalText;
    }

    public String toFileString() {
        return goalText + ";" + isCompleted;
    }

    public static Goal fromFileString(String line) {
        String[] parts = line.split(";");
        return new Goal(parts[0], Boolean.parseBoolean(parts[1]));
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getGoalText() {
        return this.goalText;
    }
}
