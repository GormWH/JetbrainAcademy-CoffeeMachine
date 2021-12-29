package machine;

import java.util.Scanner;

public class CoffeeMachine {
    // field
    int water, milk, coffeeBeans, cups, money;

    // constructor
    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }

    public class Recipe {
        int water, milk, coffeeBeans, price;

        public Recipe(int water, int milk, int coffeeBeans, int price) {
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.price = price;
        }
    }

    public void buy(String choice) {
        Recipe recipe = null;
        switch (choice) {
            case "1":
                recipe = new Recipe(250, 0, 16, 4); // espresso
                makeAndGetPaid(recipe);
                break;
            case "2":
                recipe = new Recipe(350, 75, 20, 7); // latte
                makeAndGetPaid(recipe);
                break;
            case "3":
                recipe = new Recipe(200, 100, 12, 6); // cappuccino
                makeAndGetPaid(recipe);
                break;
            case "back":
                break;
        }
    }

    private void makeAndGetPaid(Recipe recipe) {
        if (recipe == null) {
            recipe = new Recipe(0, 0, 0, 0);
        }
        if (recipe.water > water) {
            System.out.println("Sorry, not enough water!");
        } else if (recipe.milk > milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (recipe.coffeeBeans > coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (1 > cups) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= recipe.water;
            milk -= recipe.milk;
            coffeeBeans -= recipe.coffeeBeans;
            cups--;
            money += recipe.price;
        }
    }

    public void fill(String choice, int amount) {
        switch (choice) {
            case "water":
                water += amount;
                break;
            case "milk":
                milk += amount;
                break;
            case "coffee beans":
                coffeeBeans += amount;
                break;
            case "cups":
                cups += amount;
                break;
        }
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void remaining() {
        System.out.printf("%d ml of water%n", water);
        System.out.printf("%d ml of milk%n", milk);
        System.out.printf("%d g of coffee beans%n", coffeeBeans);
        System.out.printf("%d disposable cups%n", cups);
        System.out.printf("$%d of money%n", money);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // coffee machine initialization
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);

        boolean onOperation = true;
        while (onOperation) {
            // get action from std input
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

            // do action
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String choice = scanner.next();
                    coffeeMachine.buy(choice);
                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add:");
                    coffeeMachine.fill("water", Integer.parseInt(scanner.next()));
                    System.out.println("Write how many ml of milk you want to add:");
                    coffeeMachine.fill("milk", Integer.parseInt(scanner.next()));
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    coffeeMachine.fill("coffee beans", Integer.parseInt(scanner.next()));
                    System.out.println("Write how many disposable cups you want to add:");
                    coffeeMachine.fill("cups", Integer.parseInt(scanner.next()));
                    break;
                case "take":
                    coffeeMachine.take();
                    break;
                case "remaining":
                    coffeeMachine.remaining();
                    break;
                case "exit":
                    onOperation = false;
                    break;
                default:
                    System.out.println("No such action available");
                    break;
            }
        }
    }
}
