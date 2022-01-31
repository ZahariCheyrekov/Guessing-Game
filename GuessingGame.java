import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

class GuessingGame {
    private static final Random random = new Random();
    private static final int randomNumber = random.nextInt(100);
    private static int numberOfTries = 0;
    private static int numberOfInvalidCommands = 0;
    private static boolean guessIt = false;
    private static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayStartingMessages();

        String input;

        while (!guessIt) {
            System.out.println("Guess a number between 1 and 100:");
            input = scanner.nextLine();

            if (Pattern.matches("^[\\d]+$", input)) {
                numberOfTries++;

                if (numberOfTries == 10) {

                    if (!guessIt) {
                        gameOver = true;
                        System.out.println("Sorry. You couldn't guess the number. The number was " + randomNumber + ".");

                    } else {
                        System.out.println("Congratulations! You guess the number.");
                        System.out.println("Number of tries: " + numberOfTries);
                    }

                } else {
                    checkTheNumber(input);
                }

                if (guessIt && numberOfTries < 11) {
                    System.out.println("Congratulations! You guess the number.");
                    System.out.println("Number of tries: " + numberOfTries);
                }

            } else {
                numberOfInvalidCommands++;
                System.out.println("Enter number in the range [1...100]");
            }

            if (gameOver) {
                break;
            }

            if (numberOfInvalidCommands == 5) {
                System.out.println("Sorry, too many invalid commands. Try again.");
            }
        }
    }

    private static void checkTheNumber(String input) {
        if (input.length() > 3) {
            System.out.println("Enter number in the range [1...100]");
            numberOfInvalidCommands++;

        } else {
            int enteredNumber = Integer.parseInt(input);

            if (enteredNumber < randomNumber) {
                System.out.println("The number is greater than " + enteredNumber);

            } else if (enteredNumber > randomNumber) {
                System.out.println("The number is lower than " + enteredNumber);

            } else {
                guessIt = true;
            }
        }
    }

    private static void displayStartingMessages() {
        System.out.println("Welcome to the number guessing game." + System.lineSeparator() +
                "You have only 10 tries." + System.lineSeparator() +
                "Try your best to guess random number in the range [1..100]." + System.lineSeparator());
    }
}