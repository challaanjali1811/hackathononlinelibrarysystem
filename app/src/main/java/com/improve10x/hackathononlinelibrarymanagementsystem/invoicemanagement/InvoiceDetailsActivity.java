package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement;

import android.os.Bundle;

import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity;
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book;
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.InvoiceDetailsBinding;

public class InvoiceDetailsActivity extends BaseActivity {

    private Invoice invoice;
    private InvoiceDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InvoiceDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Invoice Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        invoice = (Invoice) getIntent().getSerializableExtra("invoice");
        showData();
    }

    private void showData() {
        binding.invoiceIdTxt.setText("Invoice ID : " + invoice.getId());
        binding.titleTxt.setText("Title : " + invoice.getBook().getTitle());
        binding.priceTxt.setText("Rs. " + invoice.getPaymentInfo().getTotalPrice());
        binding.paymentModeTxt.setText("Payment via : " + invoice.getPaymentInfo().getPaymentMode());
        binding.gstTxt.setText("GST(18%) Rs. " + invoice.getPaymentInfo().getGstPrice());
        binding.publishedDateTxt.setText("Date : " + invoice.getBook().getPublishedDate());
        binding.versionTxt.setText(invoice.getBook().getVersion());
        binding.appNameTxt.setText("Partner : " + invoice.getAppName());
        binding.buyerNameTxt.setText("Name : " + invoice.getBuyerDetails().getName());
        binding.buyerMobileTxt.setText("Mobile : " + invoice.getBuyerDetails().getMobile());
        binding.buyerEmailTxt.setText("Email : " + invoice.getBuyerDetails().getEmail());
        binding.buyerAddressTxt.setText("Address : " + invoice.getBuyerDetails().getAddress());
        binding.sellerNameTxt.setText("Name : " + invoice.getSellerDetails().getName());
        binding.sellerMobileTxt.setText("Mobile : " + invoice.getSellerDetails().getMobile());
        binding.sellerEmailTxt.setText("Email : " + invoice.getSellerDetails().getEmail());
        binding.sellerPanTxt.setText("PAN : " + invoice.getSellerDetails().getPAN());
        binding.sellerGstnoTxt.setText("GST No : " + invoice.getSellerDetails().getGSTNo());
        binding.sellerAddressTxt.setText("Address : " + invoice.getSellerDetails().getAddress());


    }
}