import java.io.*;
import java.util.*;

public class DTrack {
    static Scanner sc = new Scanner(System.in);
    static final String LOG_FILE = "logs.txt";
    static final String GOALS_FILE = "goals.txt";
    static final String REMINDER_FILE = "reminders.txt";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== DINESH'S D-TRACK ====");
            System.out.println("1. Log Today’s Work");
            System.out.println("2. View All Logs");
            System.out.println("3. Exit");
            System.out.println("4. Add Goal");
            System.out.println("5. View Goals");
            System.out.println("6. Mark Goal as Completed");
            System.out.println("7. Add Reminder");
            System.out.println("8. View Reminders");
            System.out.print("> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> logWork();
                case 2 -> viewLogs();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                case 4 -> addGoal();
                case 5 -> viewGoals();
                case 6 -> markGoalCompleted();
                case 7 -> addReminder();
                case 8 -> viewReminders();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void logWork() {
        System.out.print("What did you work on today? ");
        String entry = sc.nextLine();
        String log = new Date() + " - " + entry;

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(log + "\n");
            System.out.println("Log saved.");
        } catch (IOException e) {
            System.out.println("Error saving log.");
        }
    }

    static void viewLogs() {
        System.out.println("\n--- Your Logs ---");
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("No logs found.");
        }
    }

    static void addGoal() {
        System.out.print("Enter your new goal: ");
        String goalText = sc.nextLine();
        Goal goal = new Goal(goalText);
        try (FileWriter fw = new FileWriter(GOALS_FILE, true)) {
            fw.write(goal.toFileString() + "\n");
            System.out.println("Goal added!");
        } catch (IOException e) {
            System.out.println("Error saving goal.");
        }
    }

    static void viewGoals() {
        System.out.println("\n--- Your Goals ---");
        try (BufferedReader br = new BufferedReader(new FileReader(GOALS_FILE))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                Goal g = Goal.fromFileString(line);
                System.out.println(i++ + ". " + g);
            }
        } catch (IOException e) {
            System.out.println("No goals found.");
        }
    }

    static void markGoalCompleted() {
        List<Goal> goalList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(GOALS_FILE))) {
            String line;
            while ((line = br.readLine()) != null)
                goalList.add(Goal.fromFileString(line));
        } catch (IOException e) {
            System.out.println("Error reading goals.");
            return;
        }

        viewGoals();
        System.out.print("Enter goal number to mark complete: ");
        int index = sc.nextInt(); sc.nextLine();

        if (index <= 0 || index > goalList.size()) {
            System.out.println("Invalid index.");
            return;
        }

        goalList.get(index - 1).markCompleted();

        try (FileWriter fw = new FileWriter(GOALS_FILE)) {
            for (Goal g : goalList)
                fw.write(g.toFileString() + "\n");
            System.out.println("Goal marked as completed!");
        } catch (IOException e) {
            System.out.println("Error updating goal.");
        }
    }

    static void addReminder() {
        System.out.print("Enter reminder text: ");
        String text = sc.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        Reminder r = new Reminder(text, date);

        try (FileWriter fw = new FileWriter(REMINDER_FILE, true)) {
            fw.write(r.toFileString() + "\n");
            System.out.println("Reminder saved.");
        } catch (IOException e) {
            System.out.println("Error saving reminder.");
        }
    }

    static void viewReminders() {
        System.out.println("\n--- Your Reminders ---");
        try (BufferedReader br = new BufferedReader(new FileReader(REMINDER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Reminder r = Reminder.fromFileString(line);
                System.out.println(r);
            }
        } catch (IOException e) {
            System.out.println("No reminders found.");
        }
    }
}
