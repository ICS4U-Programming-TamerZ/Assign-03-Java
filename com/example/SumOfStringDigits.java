/**
 * This program reads integers from a file and calculates the sum of their digits.
 * It also provides statistics such as total numbers processed, total sum of digits,
 * average sum of digits, minimum sum of digits, and maximum sum of digits.
 * 
 * @author Tamer Zreg
 * @since 2024-05-09
 * @version 1.0
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SumOfStringDigits {
    public static void main(String[] args) {
        String inputFilename;
        String outputFilename = "output.txt"; // Output file name

        // Check if the user has provided the input file name as a command-line argument
        if (args.length == 1) {
            inputFilename = args[0];
        } else {
            // Prompt the user to enter the filename
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the input file name: ");
            inputFilename = scanner.nextLine();
            scanner.close();
        }

        // Initialize variables to store statistics about the numbers read from the file
        int totalNumbers = 0;
        int totalSum = 0;
        int minSum = Integer.MAX_VALUE;
        int maxSum = Integer.MIN_VALUE;

        try {
            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

            String line;
            // Process each line in the file
            while ((line = reader.readLine()) != null) {
                // Check if the line contains a valid integer
                try {
                    String[] numbers = line.split("\\s+");
                    for (String num : numbers) {
                        int number = Integer.parseInt(num);
                        // Calculate the sum of digits for the current number
                        totalNumbers++; // Increment the total number count
                        int sum = RecSumOfDigits(Math.abs(number)); // Calculate the sum of digits
                        totalSum += sum; // Add the sum to the total sum
                        minSum = Math.min(minSum, sum); // Update the minimum sum
                        maxSum = Math.max(maxSum, sum); // Update the maximum sum

                        // Write the sum of digits to the output file, considering the sign of the original number
                        if (number < 0) {
                            writer.write("Sum of digits for " + number + ": -" + sum + "\n");
                        } else {
                            writer.write("Sum of digits for " + number + ": " + sum + "\n");
                        }
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the line does not contain a valid integer
                    writer.write("Invalid number format in line: " + line + "\n");
                }
            }
            reader.close(); // Close the file reader

            // Write statistics about the numbers read from the file to the output file
            writer.write("Total numbers processed: " + totalNumbers + "\n");
            writer.write("Total sum of all digits: " + totalSum + "\n");
            writer.write("Average sum of digits: " + (totalNumbers > 0 ? (double) totalSum / totalNumbers : 0) + "\n");
            writer.write("Minimum sum of digits: " + (minSum == Integer.MAX_VALUE ? "N/A" : minSum) + "\n");
            writer.write("Maximum sum of digits: " + (maxSum == Integer.MIN_VALUE ? "N/A" : maxSum) + "\n");

            writer.close(); // Close the file writer

            System.out.println("Output written to " + outputFilename);
        } catch (IOException e) {
            // Handle the case where an error occurs while reading the file
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to calculate the sum of digits of a number recursively
    public static int RecSumOfDigits(int someNumber) {
        // Base case: if the number is a single digit, return it
        if (someNumber < 10) {
            return someNumber;
        }
        // Recursive case: sum the last digit with the sum of the rest of the digits
        else {
            return someNumber % 10 + RecSumOfDigits(someNumber / 10);
        }
    }
}
