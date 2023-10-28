package com.improve10x.hackathononlinelibrarymanagementsystem;

public class Invoice {
    public Invoice() {
    }

    public Invoice(String id, String buyerDetails, String sellerDetails, String appName, String purchaseDate, String quantity, String orderId, String paymentInfo, String book) {
        this.id = id;
        this.buyerDetails = buyerDetails;
        this.sellerDetails = sellerDetails;
        this.appName = appName;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.orderId = orderId;
        this.paymentInfo = paymentInfo;
        this.book = book;
    }

    public String id;
        public String buyerDetails;
        public String sellerDetails;
        public String appName;
        public String purchaseDate;
        public String quantity;
        public String orderId;
        public String paymentInfo;
        public String book;
}
