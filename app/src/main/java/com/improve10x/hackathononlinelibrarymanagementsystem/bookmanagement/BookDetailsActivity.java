package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement;

import android.os.Bundle;

import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity;
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookDetailsBinding;
import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends BaseActivity {

    private Book book;
    private BookDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Book Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        book = (Book) getIntent().getSerializableExtra("book");
        showData();
    }

    private void showData() {
        binding.titleTxt.setText("Title : " + book.getTitle());
        binding.authorTxt.setText("Author : " + book.getAuthor());
        binding.genreTxt.setText("Genre : " + book.getGenre());
        binding.priceTxt.setText("Price : Rs. " + book.getPrice());
        binding.publishedDateTxt.setText("Published Date : " + book.getPublishedDate());
        binding.totalUnitsTxt.setText("Total Units : " + book.getQty());
        binding.unitsLeftTxt.setText("Units Remaining : " + book.getUnitsLeft());
        binding.unitsSoldTxt.setText("Units Sold : " + book.getUnitsSold());
        binding.versionTxt.setText("Version : " + book.getVersion());
        Picasso.get().load(book.getUrl()).into(binding.bookIv);
//        if (task.status.equalsIgnoreCase("Pending")) {
//            pendingStatus();
//        } else if (task.status.equalsIgnoreCase("Do Completed")) {
//            doCompletedStatus();
//        } else if (task.status.equalsIgnoreCase("'Do' not completed")) {
//            doNotCompletedStatus();
//        } else if (task.status.equalsIgnoreCase("Dare Completed")) {
//            dareCompletedStatus();
//        }
    }
//
//    private void pendingStatus() {
//        binding.doCompletedBtn.setVisibility(View.VISIBLE);
//        binding.materialCardView.setVisibility(View.GONE);
//        handleTaskCompletedBtn();
//    }
//
//    private void doCompletedStatus() {
//        binding.materialCardView.setVisibility(View.VISIBLE);
//        binding.doCompletedBtn.setVisibility(View.GONE);
//        Picasso.get().load("https://lh4.googleusercontent.com/proxy/rwcYSyNHPdrUh70BGjqH9bNpQzMphBVK52yd8xkGlmvGe88XvtxHMf6WbFNLa7-m8TxjfNpywo9rBYnef1T2joyl3W8n6qSiLbm6e_BIAzrq9GDzlZnw9caLboxKgqrg5c80zD6i68eXisTUBQ").into(binding.wishesImg);
//        binding.informTxt.setText("You have successfully completed your task");
//    }
//
//    private void doNotCompletedStatus() {
//        binding.materialCardView.setVisibility(View.VISIBLE);
//        Picasso.get().load("https://www.linkpicture.com/q/unnamed_28.png").into(binding.wishesImg);
//        binding.informTxt.setText("You haven't completed the 'Do' task \uD83D\uDE46\uD83C\uDFFB\u200D♂️, so 'Dare' needs to be completed\uD83E\uDD37\uD83C\uDFFB\u200D♂️");
//        binding.dareCompletedBtn.setVisibility(View.VISIBLE);
//        handleDareCompletedBtn();
//    }
//
//    private void dareCompletedStatus() {
//        binding.materialCardView.setVisibility(View.VISIBLE);
//        binding.dareCompletedBtn.setVisibility(View.GONE);
//        binding.informTxt.setText("If you know you can do better.. then do better.");
//        Picasso.get().load("https://lh4.googleusercontent.com/proxy/OwzUIh7P-Thx34_WKd5bwJfn0hLRmSVUK3nb3I-ZIcuQ9EHnXZN6-tYbHC-lP_NJD4mNHuUAtcJXQYIfky8WrUM1zvDvEPtbFF6vGwbEg-4z").into(binding.wishesImg);
//    }
//
//    private void handleTaskCompletedBtn() {
//        binding.doCompletedBtn.setOnClickListener(view -> {
//            task.doItem.status = "Completed";
//            task.status = "Do Completed";
//            updateTask(task);
//            doCompletedStatus();
//        });
//    }
//
//    private void handleDareCompletedBtn() {
//        binding.dareCompletedBtn.setOnClickListener(view -> {
//            task.dare.status = "completed";
//            task.status = "Dare Completed";
//            updateTask(task);
//            dareCompletedStatus();
//        });
//    }
//
//    private void updateTask(Task task) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("tasks")
//                .document(task.id)
//                .set(task)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        showToast("Updated");
//                        showData();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        showToast("Failed to update");
//                    }
//                });
//    }
}