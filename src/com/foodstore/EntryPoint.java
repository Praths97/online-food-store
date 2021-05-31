package com.foodstore;

import com.foodstore.model.*;
import com.foodstore.services.menuitems.MenuItemService;
import com.foodstore.services.menuitems.MenuItemServiceImplementation;
import com.foodstore.services.orders.OrderService;
import com.foodstore.services.orders.OrderServiceImplementation;

import java.util.List;
import java.util.Scanner;

public class EntryPoint {
    public static String menu = "MENU";
    public static String ORDER = "ORDER";
    public static String LIST_ORDERS = "LIST_ORDERS";
    static OrderService orderService = null;
    static MenuItemService menuItemService = null;

    public static void main(String[] args) {
        orderService = new OrderServiceImplementation();
        menuItemService = new MenuItemServiceImplementation();
        System.out.println("*=*=*=*=* WELCOME TO ONLINE FOOD STORE *=*=*=*=*");
        printOptions();
    }

    public static void readInput(Scanner scanner) {
        String next = scanner.next();

        if (menu.equalsIgnoreCase(next)) {
            System.out.println("======= FOOD MENU START ======");
            // write code to list the menu items
            List<MenuItem> items = menuItemService.listMenu();
            System.out.print(" Id       ");
            System.out.print(" Name     ");
            System.out.println("Price");
            for (int i = 0; i < items.size(); i++) {
                MenuItem m = items.get(i);
                System.out.print("  " + m.getId());
                System.out.print("    " + m.getName());
                System.out.println("      " + m.getPrice());
            }
            System.out.println("======= FOOD MENU END ======");
            //System.out.println();
            printOptions();
        } else if (ORDER.equalsIgnoreCase(next)) {
            Order order = new Order();
            System.out.println("Please enter menu item id");
            String itemId = scanner.next();
            order.setItemId(itemId);
            System.out.println("Please enter quantity");
            int itemQuant = Integer.parseInt(scanner.next());
            order.setQuantity(itemQuant);
            System.out.println("======= PaymentDetails =======");
            System.out.println("Total amount is :: " + (menuItemService.getMenu(itemId).getPrice() * itemQuant));
            PaymentDetails paymentDetails = new PaymentDetails();

            System.out.println("Please enter Payment Type CASH/CARD");
            String type = scanner.next();
            paymentDetails.setType(type);

            if (type.equalsIgnoreCase("card")) {
                System.out.println("please enter the card number");
                String cardNumber = scanner.next();
                paymentDetails.setCardNumber(cardNumber);
                System.out.println("please enter the card holder name");
                String cardHolderName = scanner.next();
                paymentDetails.setCardHolderName(cardHolderName);
            }
            if (type.equalsIgnoreCase("Cash")) {
                System.out.println("DeliveryExecutive will collect the cash");
            }
            order.setPaymentDetails(paymentDetails);
            System.out.println("======= ADDRESS DETAILS =======");
            AddressDetails addressDetails = new AddressDetails();
            System.out.print("Name :");
            String name = scanner.next();
            addressDetails.setCustomerName(name);
            System.out.print("PhoneNumber :");
            String number = scanner.next();
            addressDetails.setCustomerNumber(number);
            System.out.print("HouseNumber :");
            String houseNumber = scanner.next();
            addressDetails.setHouseNumber(houseNumber);
            System.out.print("Street :");
            String street = scanner.next();
            addressDetails.setStreet(street);
            System.out.print("Area :");
            String area = scanner.next();
            addressDetails.setArea(area);
            System.out.print("City :");
            String city = scanner.next();
            addressDetails.setCity(city);
            System.out.print("State :");
            String state = scanner.next();
            addressDetails.setState(state);
            System.out.print("Pincode :");
            String pincode = scanner.next();
            addressDetails.setPincode(Integer.parseInt(pincode));
            order.setAddressDetails(addressDetails);
            Order updatedOrder = orderService.placeOrder(order);
            if (updatedOrder != null) {
                System.out.println(" ========== THANKS FOR PLACING AN ORDER ============");
                System.out.println("Your order id is :: " + updatedOrder.getId());
                System.out.println("Your payment id is :: " + updatedOrder.getPaymentId());
                System.out.println("Delivery Executive details: ");
                System.out.println("        -> Name = " + order.getDeliveryExecutiveDetails().getName());
                System.out.println("        > PhoneNumber = " + order.getDeliveryExecutiveDetails().getPhoneNumber());
                System.out.println("============== FEEDBACK============");
                Feedback feedback = new Feedback();
                feedback.setCustomerName(addressDetails.getCustomerName());
                System.out.println("Please give your valuable feedback here : ");
                String message = scanner.next();
                feedback.setMessage(message);
                feedback.setOrderId(updatedOrder.getId());
                order.setFeedback(feedback);
                orderService.giveFeedback(feedback);
                System.out.println(" ===THANKS FOR YOUR FEEDBACK * SEE YOU SOON ===");
            }
            printOptions();
        } else if (LIST_ORDERS.equalsIgnoreCase(next)) {
            System.out.println("======= ORDERS LIST START ======");
            // write code to list the orders list
            List<Order> orders = orderService.listOrders();
            if (orders.size() > 0) {
                System.out.print("Id");
                System.out.print("  item name");
                System.out.print("  Quantity");
                System.out.print("  Price");
                System.out.print(" Delivered By");
                System.out.println("");
                for (int i = 0; i < orders.size(); i++) {
                    Order order = orders.get(i);
                    System.out.print(order.getId());
                    System.out.print("    " + order.getItemId());
                    System.out.print("    " + order.getQuantity());
                    System.out.print("    " + order.getPrice());
                    System.out.print("    " + order.getDeliveryExecutiveDetails().getName());
                    System.out.println();
                }
            } else {
                System.out.println("No orders");
            }
            System.out.println();
            System.out.println("======= ORDERS LIST END ======");
            System.out.println();
            printOptions();

        } else {
            System.out.println("INVALID input, try again");
            readInput(scanner);
        }

    }

    static void printOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("==============OPTIONS START===============");
        System.out.println("Type MENU to see the menu items");
        System.out.println("Type ORDER to place order");
        System.out.println("Type LIST_ORDERS to see all orders");
        System.out.println("===============OPTIONS END===============");
        readInput(scanner);
    }
}
