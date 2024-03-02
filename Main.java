package test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command (add/search/promotion/exit):");
            String command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Enter item name, price, and quantity separated by spaces:");
                    String[] itemDetails = scanner.nextLine().split(" ");
                    store.addItem(new Item(itemDetails[0], Double.parseDouble(itemDetails[1]), Integer.parseInt(itemDetails[2])));
                    break;
                case "search":
                    System.out.println("Enter item name to search:");
                    String name = scanner.nextLine();
                    Item item = store.searchItem(name);
                    if (item != null) {
                        System.out.println("Item found: " + item.getName() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case "promotion":
                    System.out.println("Enter item name and price threshold for promotion, separated by space:");
                    String[] promoDetails = scanner.nextLine().split(" ");
                    store.checkPromotion(promoDetails[0], Double.parseDouble(promoDetails[1]));
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
