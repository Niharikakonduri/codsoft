import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void checkBalance() {
        System.out.println("Your account balance is $" + balance);
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Quit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Select an option (1/2/3/4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

class Quiz {
    private String[] questions;
    private String[][] options;
    private int[] answers;
    private int score = 0;
    private int currentQuestion = 0;

    public Quiz(String[] questions, String[][] options, int[] answers) {
        this.questions = questions;
        this.options = options;
        this.answers = answers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestion < questions.length) {
            System.out.println("\nQuestion " + (currentQuestion + 1) + ": " + questions[currentQuestion]);
            for (int i = 0; i < options[currentQuestion].length; i++) {
                System.out.println((i + 1) + ". " + options[currentQuestion][i]);
            }

            System.out.print("Select an option (1/2/3/4): ");
            int answer = scanner.nextInt();

            if (answer == answers[currentQuestion]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }

            currentQuestion++;
        }

        displayResult();
        scanner.close();
    }

    public void displayResult() {
        System.out.println("\nYour final score: " + score + "/" + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
        atm.run();

        String[] quizQuestions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal in the world?"
        };

        String[][] quizOptions = {
            {"London", "Berlin", "Paris", "Rome"},
            {"Earth", "Mars", "Venus", "Jupiter"},
            {"Elephant", "Giraffe", "Blue Whale", "Hippopotamus"}
        };

        int[] quizAnswers = {3, 2, 3};

        Quiz quiz = new Quiz(quizQuestions, quizOptions, quizAnswers);
        quiz.run();
    }
}