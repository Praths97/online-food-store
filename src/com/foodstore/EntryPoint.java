package com.foodstore;

import java.util.Scanner;

public class EntryPoint {
    public static String menu = "MENU";
    public static String order = "ORDER";
    //public static String type;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*=*=*=*=* WELCOME *=*=*=*=*");
        System.out.println("Type MENU to see the menu items");
        readInput(scanner);
    }

    public static void readInput(Scanner scanner) {
        String next = scanner.next();

        if (menu.equalsIgnoreCase(next)) {
            System.out.println("======= FOOD MENU ======");
            // write code to list the menu items

            System.out.println("======= Order Details =======");
            System.out.println("Type ORDER to place an order");
            readInput(scanner);
        } else if (order.equalsIgnoreCase(next)) {
            System.out.println("Please enter menu item id");
            String itemId = scanner.next();
            System.out.println("Please enter quantity");
            String itemQuant = scanner.next();
            System.out.println("======= PaymentDetails =======");
            System.out.println("Please enter Payment Type");
            String type = scanner.next();

            if (type.equalsIgnoreCase("card")) {
                System.out.println("please enter the last 4 digits of card");
                String cardNumber = scanner.next();
                System.out.println("please enter the card holder name");
                String cardHolderName = scanner.next();
                System.out.println("please enter ");
            }
            if (type.equalsIgnoreCase("upi")) {
                System.out.println("please enter your upi id");
                String upi = scanner.next();
            }

            if (type.equalsIgnoreCase("InternetBanking")) {
                System.out.println("please enter your CustId");
                String custId = scanner.next();
                System.out.println("please enter your mPin");
                String pin = scanner.next();
            }
            if (type.equalsIgnoreCase("Cash")){
                System.out.println("DeliveryExecutive will collect the cash");

            }
            System.out.println("======= ADDRESS DETAILS =======");
            System.out.print("Name :");
            String name = scanner.next();
            System.out.print("PhoneNumber :");
            String number = scanner.next();
            System.out.print("HouseNumber :");
            String houseNumber = scanner.next();
            System.out.print("Street :");
            String street = scanner.next();
            System.out.print("Area :");
            String area = scanner.next();
            System.out.print("City :");
            String city = scanner.next();
            System.out.print("State :");
            String state = scanner.next();
            System.out.print("Pincode :");
            String pincode = scanner.next();
        } else {
            System.out.println("INVALID input, try again");
            readInput(scanner);
        }

    }
}
