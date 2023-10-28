package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.UserItemBinding;


public class UserViewHolder extends RecyclerView.ViewHolder {

    UserItemBinding binding;

    public UserViewHolder(UserItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
