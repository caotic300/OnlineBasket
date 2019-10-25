package com.Pedro;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket pedroBasker = new Basket("Tim");

        sellItem(pedroBasker, "car", 1);
        System.out.println(pedroBasker);

        sellItem(pedroBasker, "car", 1);
        System.out.println(pedroBasker);

        if (sellItem(pedroBasker, "car", 1) != 1) {
            System.out.println("There are no more cars in stock");
        }

        sellItem(pedroBasker, "spanner", 5);
        //System.out.println(pedroBasker);

        sellItem(pedroBasker, "juice", 4);
        sellItem(pedroBasker, "cup", 12);
        removeItem(pedroBasker, "bread", 1);
        //System.out.println(pedroBasker);
        //System.out.println(stockList);

        Basket basket = new Basket("customer");
        sellItem(basket,"cup", 100);
        sellItem(basket, "juice", 5);
        sellItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(pedroBasker, "car", 1);
        removeItem(pedroBasker, "cup", 9);
        removeItem(pedroBasker, "car", 1);
        System.out.println("cars removed: " + removeItem(pedroBasker, "car", 1));

        removeItem(pedroBasker, "bread", 1);
        removeItem(pedroBasker, "cup", 3);
        removeItem(pedroBasker, "juice", 4);
        removeItem(pedroBasker, "cup", 3);
        System.out.println(pedroBasker);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

        StockItem car = stockList.get("car");
        if (car != null) {
            car.adjustStock(2000);
        }

        if (car != null) {
            stockList.get("car").adjustStock(-1000);
        }
        stockList.Items().get("car").adjustStock(2000);
        stockList.get("car").adjustStock(-1000);

//        for (Map.Entry<String, Double> price : stockList.priceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }


    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }

}
