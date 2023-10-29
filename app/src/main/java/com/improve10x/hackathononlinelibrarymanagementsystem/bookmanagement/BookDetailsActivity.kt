package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.os.Bundle
import android.view.View
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookDetailsBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.squareup.picasso.Picasso

class BookDetailsActivity : BaseActivity() {
    private var book: Book? = null
    private var role: String? = null
    private var binding: BookDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookDetailsBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
        supportActionBar!!.title = "Book Details"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        book = intent.getSerializableExtra("book") as Book?
        role = intent.getStringExtra("role")
        handleVisibility()
        showData()
    }

    private fun handleVisibility() {
        when (role) {
            "Buyer" -> {
                binding!!.unitsSoldTxt.visibility = View.GONE
                binding!!.totalUnitsTxt.visibility = View.GONE
                binding!!.unitsLeftTxt.visibility = View.GONE
                if(UserMgr.getCurrentUser()?.books?.filter { it.id == book?.id }?.isEmpty() == true){
                    binding!!.downloadBtn.visibility = View.INVISIBLE
                }
                else{
                    binding!!.downloadBtn.visibility = View.VISIBLE
                }
            }
            else -> {
                binding!!.unitsSoldTxt.visibility = View.VISIBLE
                binding!!.totalUnitsTxt.visibility = View.VISIBLE
                binding!!.unitsLeftTxt.visibility = View.VISIBLE
            }
        }
    }

    private fun showData() {
        binding!!.titleTxt.text = "Title : " + book!!.title
        binding!!.authorTxt.text = "Author : " + book!!.author
        binding!!.genreTxt.text = "Genre : " + book!!.genre
        binding!!.priceTxt.text = "Price : Rs. " + book!!.price
        binding!!.publishedDateTxt.text = "Published Date : " + book!!.publishedDate
        binding!!.totalUnitsTxt.text = "Total Units : " + book!!.qty
        binding!!.unitsLeftTxt.text = "Units Remaining : " + book!!.unitsLeft
        binding!!.unitsSoldTxt.text = "Units Sold : " + book!!.unitsSold
        binding!!.versionTxt.text = "Version : " + book!!.version
        Picasso.get().load(book!!.url).into(binding!!.bookIv)
        //        if (task.status.equalsIgnoreCase("Pending")) {
//            pendingStatus();
//        } else if (task.status.equalsIgnoreCase("Do Completed")) {
//            doCompletedStatus();
//        } else if (task.status.equalsIgnoreCase("'Do' not completed")) {
//            doNotCompletedStatus();
//        } else if (task.status.equalsIgnoreCase("Dare Completed")) {
//            dareCompletedStatus();
//        }
    } //
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