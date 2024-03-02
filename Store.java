package test;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Store {
    Trie items;
    List<Item> itemList; // Used for promotions

    public Store() {
        items = new Trie();
        itemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.insert(item.getName().toLowerCase(), item);
        itemList.add(item);
    }

    public Item searchItem(String name) {
        return items.search(name.toLowerCase());
    }


    public void checkPromotion(String itemName, double priceThreshold) {
        Item chosenItem = searchItem(itemName.toLowerCase());
        if (chosenItem == null) {
            System.out.println("Item not found.");
            return;
        }

        // Sort itemList by price
        Collections.sort(itemList, Comparator.comparingDouble(Item::getPrice));

        // Perform binary search to find a suitable promotion partner
        int resultIndex = binarySearchForPromotion(chosenItem, priceThreshold);
        if (resultIndex != -1) {
            Item promotionPartner = itemList.get(resultIndex);
            System.out.println("Promotion found: " + chosenItem.getName() + " + " + promotionPartner.getName() + " exceeds the threshold with a total price of " + (chosenItem.getPrice() + promotionPartner.getPrice()));
        } else {
            System.out.println("No suitable promotion partner found for " + chosenItem.getName());
        }
    }

    private int binarySearchForPromotion(Item chosenItem, double priceThreshold) {
        int low = 0;
        int high = itemList.size() - 1;
        int bestFitIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Item midItem = itemList.get(mid);

            double total = chosenItem.getPrice() + midItem.getPrice();
            if (total < priceThreshold) {
                low = mid + 1;
            } else {
                bestFitIndex = mid;
                high = mid - 1; // Look for a closer threshold exceed but not too far
            }
        }

        return bestFitIndex;
    }
}
