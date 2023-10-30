package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.UserItemBinding;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserInf> userList;
    private OnUserItemActionListener onUserItemActionListener;

    void setData(List<UserInf> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnUserItemActionListener onItemActionListener) {
        this.onUserItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(binding);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserInf user = userList.get(position);
        holder.binding.idTxt.setText("" + user.getId());
        holder.binding.nameTxt.setText(" " + user.getName());
        holder.binding.editBtn.setOnClickListener(view -> {
            onUserItemActionListener.onEditClicked(user);
            Log.d("Online Library : User", "Editing user.");
        });
        holder.binding.deleteBtn.setOnClickListener(view -> {
            Log.d("Online Library : User", "Deleting user.");
            onUserItemActionListener.onDeleteClicked(user);
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
