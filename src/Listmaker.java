import java.util.ArrayList;
import java.util.Scanner;

class ListMaker {
    private static final ArrayList<String> myArrList = new ArrayList<>(); // ArrayList to store strings
    private static final Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args)
    {
        char choice = '\0'; // Initialize choice with a default value
        while (choice != 'Q' && choice != 'q')
        {
            displayMenu(); // Display the menu
            choice = getRegExString(); // Get user choice from the menu
            switch (choice) {
                case 'A':
                case 'a':
                    addItemToList(); // Add an item to the list
                    break;
                case 'D':
                case 'd':
                    deleteItemFromList(); // Delete an item from the list
                    break;
                case 'P':
                case 'p':
                    printList(); // Print the list
                    break;
                case 'Q':
                case 'q':
                    if (confirmQuit()) { // Prompt for quitting the program
                        System.out.println("Exiting program..."); // If user confirms, exit the program
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid input! Please enter a valid menu option.");
            }
        }
    }

    private static void displayMenu() // Display the menu options
    {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
        System.out.print("Enter your choice: ");
    }

    private static void addItemToList()  // Add an item to the list
    {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        myArrList.add(item);
        System.out.println("Item added successfully!");
    }

    private static void deleteItemFromList() // Delete an item from the list
    {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        System.out.println("Current items in the list:");
        printNumberedList();

        System.out.print("Enter the number of the item to delete: ");
        int index = getRangedInt(myArrList.size()) - 1;
        String deletedItem = myArrList.remove(index);
        System.out.println("Item '" + deletedItem + "' deleted successfully!");
    }

    private static void printList() // Print/display the list
    {
        if (myArrList.isEmpty())
        {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Current items in the list:");
        for (String item : myArrList)
        {
            System.out.println("- " + item);
        }
    }

    private static boolean confirmQuit() // Prompt for quitting the program
    {
        System.out.print("Are you sure you want to quit? (Y/N): ");
        return getYNConfirm(); // Return true if user confirms, false otherwise
    }

    private static void printNumberedList() // Display a numbered version of the list
    {
        for (int i = 0; i < myArrList.size(); i++)
        {
            System.out.println((i + 1) + ". " + myArrList.get(i));
        }
    }

    // Utility methods for input validation

    private static char getRegExString() // Validate user input based on regex pattern
    {
        String input;
        do
        {
            input = scanner.nextLine().trim();
            if (!input.matches("[AaDdPpQq]"))
            {
                System.out.println("Invalid input! Please enter a valid menu option.");
            }
        } while (!input.matches("[AaDdPpQq]"));
        return input.charAt(0);
    }

    private static int getRangedInt(int max) // Validate and return an integer within the specified range
    {
        int num;
        do
        {
            while (!scanner.hasNextInt())
            {
                System.out.print("Invalid input! Enter a number: ");
                scanner.next();
            }
            num = scanner.nextInt();
            if (num < 1 || num > max)
            {
                System.out.print("Invalid input! Enter a number between " + 1 + " and " + max + ": ");
            }
        } while (num < 1 || num > max);
        scanner.nextLine(); // Consume newline
        return num;
    }

    private static boolean getYNConfirm() // Validate yes/no confirmation
    {
        String input;
        do
        {
            input = scanner.nextLine().trim().toUpperCase();
        } while (!input.equals("Y") && !input.equals("N"));
        return input.equals("Y");
    }
}