package machine;

import java.util.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (!str.equals("exit")) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
            str = sc.next();
            switch (str) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                            "back - to main menu: ");
                    String s = sc.next();
                    if (s.equals("back")) {
                        break;
                    }
                    CoffeeMachinAction.buyCoffee(s);
                    break;
                case "fill":
                    CoffeeMachinAction.fillResources();
                    break;
                case "take":
                    CoffeeMachinAction.takeMoney();
                    break;
                case "remaining":
                    CoffeeMachinAction.printStatistic();
                    break;
            }
        }
    }
}

enum CoffeeType {
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1);

    int watter, milk, coffeeBeans, cost, cups;

    CoffeeType(int watter, int milk, int coffeeBeans, int cost, int cups) {
        this.watter = watter;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
        this.cups = cups;
    }
}

class CoffeeMachinAction {
    enum machineResources {
        RESOURCES(400, 540, 120, 550, 9);
        int water, milk, coffeeBeans, money, cups;

        machineResources(int watter, int milk, int coffeeBeans, int money, int cups) {
            this.water = watter;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.money = money;
            this.cups = cups;
        }
    }

    static void buyCoffee(String s) {
        switch (s) {
            case "1":
                s = "ESPRESSO";
                break;
            case "2":
                s = "LATTE";
                break;
            case "3":
                s = "CAPPUCCINO";
        }
        System.out.println(amountOfIngredients(s));

        if (amountOfIngredients(s) == "I have enough resources, making you a coffee!") {
            machineResources.RESOURCES.water -= CoffeeType.valueOf(s).watter;
            machineResources.RESOURCES.milk -= CoffeeType.valueOf(s).milk;
            machineResources.RESOURCES.coffeeBeans -= CoffeeType.valueOf(s).coffeeBeans;
            machineResources.RESOURCES.cups -= CoffeeType.valueOf(s).cups;
            machineResources.RESOURCES.money += CoffeeType.valueOf(s).cost;
        }
    }

    static void fillResources() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add: ");
        int water = sc.nextInt();
        machineResources.RESOURCES.water += water;
        System.out.println("Write how many ml of milk do you want to add: ");
        int milk = sc.nextInt();
        machineResources.RESOURCES.milk += milk;
        System.out.println("Write how many grams of coffee beans do you want to add");
        int coffeeBeans = sc.nextInt();
        machineResources.RESOURCES.coffeeBeans += coffeeBeans;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = sc.nextInt();
        machineResources.RESOURCES.cups += cups;
    }

    static void takeMoney() {
        System.out.println("I gave you $" + machineResources.RESOURCES.money);
        machineResources.RESOURCES.money = 0;
    }

    static String amountOfIngredients(String s) {
        int water = machineResources.RESOURCES.water;
        int milk = CoffeeType.valueOf(s).milk;
        int coffeeBeans = CoffeeType.valueOf(s).coffeeBeans;
        int cups = machineResources.RESOURCES.cups;
        String str = "";

        if (water >= CoffeeType.valueOf(s).watter && milk >= CoffeeType.valueOf(s).milk
                && coffeeBeans >= CoffeeType.valueOf(s).coffeeBeans) {
            str = "I have enough resources, making you a coffee!";
        } else if (water < CoffeeType.valueOf(s).watter) {
            str = "Sorry, not enough water!";
        } else if (water < CoffeeType.valueOf(s).milk) {
            str = "Sorry, not enough milk!";
        } else if (water < CoffeeType.valueOf(s).coffeeBeans) {
            str = "Sorry, not enough coffee beans!";
        } else if (water < CoffeeType.valueOf(s).cups) {
            str = "Sorry, not enough cups!";
        }
        return str;
    }

    static void printStatistic() {
        System.out.println("\nThe coffee machine has:\n" +
                machineResources.RESOURCES.water + " of water\n" +
                machineResources.RESOURCES.milk + " of milk\n" +
                machineResources.RESOURCES.coffeeBeans + " of coffee beans\n" +
                machineResources.RESOURCES.cups + " of disposable cups\n$" +
                machineResources.RESOURCES.money + " of money");
    }
}