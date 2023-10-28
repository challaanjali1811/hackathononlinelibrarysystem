package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement;

import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book;

public interface OnUserItemActionListener {

    void onEditClicked(UserInf user);
    void onDeleteClicked(UserInf user);
}
