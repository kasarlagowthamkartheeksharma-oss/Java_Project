
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Workout {
    private String exerciseName;
    private int duration;
    private double caloriesBurned;
    private LocalDate date;

    public Workout(String exerciseName, int duration,
                   double caloriesBurned) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = LocalDate.now();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getDuration() {
        return duration;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public LocalDate getDate() {
        return date;
    }

    public void displayWorkout() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("--------------------------------");
        System.out.println("Exercise       : " + exerciseName);
        System.out.println("Duration       : " + duration + " minutes");
        System.out.printf("Calories Burned: %.2f kcal%n",
                caloriesBurned);
        System.out.println("Date           : "
                + date.format(formatter));
    }
}

public class PersonalFitnessTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Workout> workouts =
            new ArrayList<>();

    // Approximate calorie estimates per minute for a 70 kg adult.
    // Actual energy expenditure varies by person and activity intensity.
    private static double estimateCalories(String exercise, int minutes) {
        double caloriesPerMinute;

        switch (exercise.toLowerCase()) {
            case "walking":
                caloriesPerMinute = 3.5;
                break;

            case "running":
                caloriesPerMinute = 10.0;
                break;

            case "cycling":
                caloriesPerMinute = 7.0;
                break;

            case "swimming":
                caloriesPerMinute = 8.0;
                break;

            case "yoga":
                caloriesPerMinute = 3.0;
                break;

            case "weight training":
                caloriesPerMinute = 5.0;
                break;

            default:
                caloriesPerMinute = 4.0;
        }

        return caloriesPerMinute * minutes;
    }

    private static void addWorkout() {
        System.out.println("\n--- Add New Workout ---");

        System.out.println("Available exercises:");
        System.out.println("1. Walking");
        System.out.println("2. Running");
        System.out.println("3. Cycling");
        System.out.println("4. Swimming");
        System.out.println("5. Yoga");
        System.out.println("6. Weight Training");
        System.out.println("7. Other");

        System.out.print("Choose exercise (1-7): ");

        int choice = readInt();

        String exercise;

        switch (choice) {
            case 1:
                exercise = "Walking";
                break;
            case 2:
                exercise = "Running";
                break;
            case 3:
                exercise = "Cycling";
                break;
            case 4:
                exercise = "Swimming";
                break;
            case 5:
                exercise = "Yoga";
                break;
            case 6:
                exercise = "Weight Training";
                break;
            case 7:
                System.out.print("Enter exercise name: ");
                exercise = scanner.nextLine().trim();

                if (exercise.isEmpty()) {
                    System.out.println("Exercise name cannot be empty.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid exercise choice.");
                return;
        }

        System.out.print("Enter workout duration in minutes: ");
        int duration = readInt();

        if (duration <= 0 || duration > 1440) {
            System.out.println(
                    "Duration must be between 1 and 1440 minutes.");
            return;
        }

        double calories = estimateCalories(exercise, duration);

        Workout workout =
                new Workout(exercise, duration, calories);

        workouts.add(workout);

        System.out.println("\nWorkout added successfully!");
        workout.displayWorkout();

        System.out.println(
                "\nNote: Calorie values are rough estimates only.");
    }

    private static void viewWorkoutHistory() {
        System.out.println("\n--- Workout History ---");

        if (workouts.isEmpty()) {
            System.out.println("No workouts recorded yet.");
            return;
        }

        for (int i = 0; i < workouts.size(); i++) {
            System.out.println("\nWorkout #" + (i + 1));
            workouts.get(i).displayWorkout();
        }
    }

    private static void viewFitnessSummary() {
        System.out.println("\n--- Fitness Summary ---");

        if (workouts.isEmpty()) {
            System.out.println("No workout data available.");
            return;
        }

        int totalDuration = 0;
        double totalCalories = 0;

        for (Workout workout : workouts) {
            totalDuration += workout.getDuration();
            totalCalories += workout.getCaloriesBurned();
        }

        System.out.println("Total Workouts   : " + workouts.size());
        System.out.println("Total Duration   : "
                + totalDuration + " minutes");
        System.out.printf("Estimated Calories: %.2f kcal%n",
                totalCalories);

        double averageDuration =
                (double) totalDuration / workouts.size();

        System.out.printf("Average Duration : %.2f minutes%n",
                averageDuration);
    }

    private static void searchWorkout() {
        System.out.print("\nEnter exercise name to search: ");
        String search = scanner.nextLine().trim();

        boolean found = false;

        for (Workout workout : workouts) {
            if (workout.getExerciseName()
                    .toLowerCase()
                    .contains(search.toLowerCase())) {

                workout.displayWorkout();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching workouts found.");
        }
    }

    private static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid whole number: ");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" PERSONAL FITNESS & WORKOUT TRACKER");
        System.out.println("======================================");
        System.out.println("KLH University");
        System.out.println("Developed by: Gowtham Sharma");
        System.out.println("Student ID: 2620030281");
        System.out.println("Team Member: Sarvan");
        System.out.println("Student ID: 2620030289");

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Workout");
            System.out.println("2. View Workout History");
            System.out.println("3. View Fitness Summary");
            System.out.println("4. Search Workout");
            System.out.println("5. Exit");
            System.out.println("===============================");

            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {

