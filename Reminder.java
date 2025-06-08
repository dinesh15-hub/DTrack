public class Reminder {
    private String text;
    private String date;

    public Reminder(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String toString() {
        return "[Reminder] " + "[" + date + "] " + text;
    }

    public String toFileString() {
        return text + ";" + date;
    }

    public static Reminder fromFileString(String line) {
        String[] parts = line.split(";");
        return new Reminder(parts[0], parts[1]);
    }
}
