package org.example;
import javafx.util.Pair;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Initialize empty database
        InMemoryDB inMemoryDB = new InMemoryDB();

        // Get user input
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        // Store status of transaction and hold temporary keys/values
        boolean transaction = false;
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<Integer>();

        do {
            // Display input menu
            System.out.println("Please enter the number of the operation you would like to perform:");
            System.out.println("1. Begin Transaction");
            System.out.println("2. Enter/Update key-value pair");
            System.out.println("3. Rollback transaction changes");
            System.out.println("4. End Transaction");
            System.out.println("5. Get value based on key");
            System.out.println("6. Exit program");

            input = scanner.nextInt();
            if (input == 1) {
                // Begin a new transaction
                if (transaction)
                {
                    System.out.print("Cannot start a new transaction while an existing transaction is in progress.");
                }
                else {
                    transaction = true;
                }
            } else if (input == 2) {
                // Enter/update key-value pair
                if (!transaction) {
                    throw new RuntimeException("No transaction in progress.");
                }
                System.out.println("Enter key:");
                String key = scanner.next();
                System.out.println("Enter value:");
                int value = scanner.nextInt();

                    int keyIndex = -1;
                    for (int i = 0; i < keys.size(); i++) {
                        if (Objects.equals(keys.get(i), key)) {
                            keyIndex = i;
                        }
                    }
                    if (keyIndex == -1) {
                        keys.add(key);
                        values.add(value);
                    }
                    else {
                        values.set(keyIndex, value);
                    }


            } else if (input == 3) {
                // End transaction
                if (!transaction) {
                    System.out.println("No transaction in progress.");
                }
                else {
                    keys.clear();
                    values.clear();
                    inMemoryDB.rollback();
                    transaction = false;
                }

            } else if (input == 4) {
                // End transaction
                if (!transaction) {
                    System.out.println("No transaction in progress.");
                }
                else {
                    inMemoryDB.commit(keys, values);
                    transaction = false;
                }
            }
            else if (input == 5) {
                // Get value
                System.out.println("Enter key:");
                String key = scanner.next();
                Integer value = inMemoryDB.get(key);
                System.out.println(value);
            }

        } while (input != 6);

    }
}