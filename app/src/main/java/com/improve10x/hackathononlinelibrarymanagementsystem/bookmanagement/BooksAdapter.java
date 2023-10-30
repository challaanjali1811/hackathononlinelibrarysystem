package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {

    public BooksAdapter(String role) {
        this.role = role;
    }


    private List<Book> bookList;
    private OnItemActionListener onItemActionListener;

    private String role;

    void setData(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding binding = BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(binding);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        switch(role){
            case "Seller" :
                holder.binding.buyBtn.setVisibility(View.GONE);
                holder.binding.editBtn2.setVisibility(View.VISIBLE);
                holder.binding.deleteBtn2.setVisibility(View.VISIBLE);
                break;
            case "Buyer" :
            default:
                holder.binding.editBtn2.setVisibility(View.GONE);
                holder.binding.deleteBtn2.setVisibility(View.GONE);
                holder.binding.buyBtn.setVisibility(View.VISIBLE);
                break;
        }
        Book book = bookList.get(position);
        holder.binding.titleTxt.setText("Title : " + book.getTitle());
        holder.binding.priceTxt.setText("Price : Rs. " + book.getPrice());
        holder.binding.bookIv.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(book);
        });
        holder.binding.titleTxt.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(book);
        });
        holder.binding.priceTxt.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(book);
        });
        holder.binding.editBtn2.setOnClickListener(view -> {
            onItemActionListener.onEditClicked(book);
            Log.d("Online Library : Book", "Editing book.");
        });
        holder.binding.deleteBtn2.setOnClickListener(view -> {
            Log.d("Online Library : Book", "Deleting book.");
            onItemActionListener.onDeleteClicked(book);
        });
        holder.binding.buyBtn.setOnClickListener(view -> {
            Log.d("Online Library : Book", "Buying book.");
            onItemActionListener.onBuyNowClicked(book);
        });
        Picasso.get().load(book.getUrl()).into(holder.binding.bookIv);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
