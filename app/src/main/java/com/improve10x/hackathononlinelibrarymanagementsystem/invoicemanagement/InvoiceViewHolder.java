package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.InvoiceItemBinding;


public class InvoiceViewHolder extends RecyclerView.ViewHolder {

    InvoiceItemBinding binding;

    public InvoiceViewHolder(InvoiceItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
