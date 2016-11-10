package school.lemon.changerequest.java.junit.hw1.task2;

import java.util.Scanner;

public class ConsoleCalculator {
    private static final String MENU = "Console calculator:\n" +
            "Enter 'add' to perform addition.\n" +
            "Enter 'sub' to perform subtraction.\n" +
            "Enter 'mul' to perform multiplication.\n" +
            "Enter 'div' to perform division.\n" +
            "Enter 'exit' to exit.\n" +
            "Enter 'help' to see help message.";

    public static void main(String[] args) {
        System.out.println(MENU);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Make your choice.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "add": {
                    System.out.println("Enter first number:");
                    int first = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter second number:");
                    int second = Integer.parseInt(scanner.nextLine());
                    System.out.println(String.format("Result of %d+%d is %d", first, second, first + second));
                    break;
                }
                case "sub": {
                    System.out.println("Enter first number:");
                    int first = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter second number:");
                    int second = Integer.parseInt(scanner.nextLine());
                    System.out.println(String.format("Result of %d-%d is %d", first, second, first - second));
                    break;
                }
                case "mul": {
                    System.out.println("Enter first number:");
                    int first = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter second number:");
                    int second = Integer.parseInt(scanner.nextLine());
                    System.out.println(String.format("Result of %d*%d is %d", first, second, first * second));
                    break;
                }
                case "div": {
                    System.out.println("Enter first number:");
                    int first = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter second number:");
                    int second = Integer.parseInt(scanner.nextLine());
                    System.out.println(String.format("Result of %d/%d is %d", first, second, first / second));
                    break;
                }
                case "help": {
                    System.out.println(MENU);
                    break;
                }
                case "exit": {
                    exit = true;
                    System.out.println("Bye-bye.");
                    break;
                }
                default:
                    System.out.println("Non-appropriate choice. please type 'help' to see help message.");
                    break;
            }
        }
    }
}
