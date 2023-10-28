package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement;

public interface OnItemActionListener {

    void onItemClicked(Book book);
    void onEditClicked(Book book);
    void onDeleteClicked(Book book);
    void onBuyNowClicked(Book book);
}
