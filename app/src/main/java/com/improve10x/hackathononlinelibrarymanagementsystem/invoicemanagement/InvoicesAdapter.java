package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.InvoiceItemBinding;

import java.util.List;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoiceViewHolder> {

    private List<Invoice> invoiceList;
    private OnItemActionListener onItemActionListener;

    void setData(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InvoiceItemBinding binding = InvoiceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        InvoiceViewHolder invoiceViewHolder = new InvoiceViewHolder(binding);
        return invoiceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Invoice invoice = invoiceList.get(position);
        holder.binding.idTxt.setText("" + invoice.getId());
        holder.binding.orderIdTxt.setText("" + invoice.getBook().getTitle());
        holder.binding.priceTv.setText("Rs. " + invoice.getPaymentInfo().getTotalPrice());
        holder.binding.viewBtn.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(invoice);
            Log.d("Online Library : Invoice", "Viewing invoice.");
        });
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }
}
