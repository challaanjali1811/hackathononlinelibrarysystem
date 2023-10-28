package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookItemBinding;


public class BookViewHolder extends RecyclerView.ViewHolder {

    BookItemBinding binding;

    public BookViewHolder(BookItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
