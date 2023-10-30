Hackathon : Online Library Management System

[Google doc link : ](https://docs.google.com/document/d/10-RK0sIRcvVJc41m88fSGFacWRuzh2DqJKPxZ5kqJ_4/edit?usp=sharing)

Features By Role :
Seller :
Using the Online Library application, the seller will be able to
Book Management System
Add books into the book inventory
Edit books into the book inventory
Delete books from the inventory
View the book details
Download books
Track sales
View sold book list
Track book units
Total number of units of a book
Remaining units of the book
Sold units of the book
View Invoice list overview
Invoice ID
Book Title
Price
View invoice details
Invoice ID, OrderId, Platform(this application), purchaseDate, quantity, orderId
Book details(Title, quantity)
Payment Details( Price , GST, Payment Mode)
Buyer details,
Seller details

Buyer :
Using the Online Library application, the buyer will be able to
Book Management System
View the book details
Browse and Search books
Simple Search
You can enter text in the search box. It will search in the book details for the search text.
Advanced Search
When selected, you will be prompted to enter individual fields for title, author and genre individually.
The compound results of three fields will be delivered.
Purchase the books
Select the payment method from available options, Pay via
Already added credit / debit card
UPI
You will be able to add your upi id
Add new credit card
Netbanking
View purchased books
Download purchased books
My orders
View purchased book list
View Invoice list overview
Invoice ID
Book Title
Price
View invoice details
Invoice ID, OrderId, Platform(this application), purchaseDate, quantity, orderId
Book details(Title, quantity)
Payment Details( Price , GST, Payment Mode)
Buyer details,
Seller details
Admin :

Using the Online Library application, the admin will be able to
User/Seller Management System
Add sellers into the user inventory
Edit sellers into the user inventory
Delete sellers from the inventory
View the seller details


Technology :
Programming Languages :  Kotlin, Java
Database : Firestore Database (Firebase)  - NoSQL DB
Books
Users
Sales and Invoices
Payment Info
Storage : Storage (Firebase)
PDF files of books
Libraries :
Picasso for rendering images of books
Gson for logging data in Json format


Design Patterns used :
Singleton DP
All db connection classes (UserNetwork, InvoiceNetwork, BookNetwork, SearchBooksNetwork)
Loggers
Singleton DP with Double Lock mechanism
All Manager classes
UserMgr
Payment Gateway Manager

Factory DP
Creating Payment Strategy (PaymentStrategyFactory)

Factory Method
Listener classes for callbacks, asynchronous communication from the firestore db (Book Management Listener,            Invoice management Listener,                             User management Listener)

Builder DP
Invoice creation (InvoiceBuilder, Create Invoice - Line 23-37)

Prototype DP
Cloning existing objects (Book clone, Buyer Details, Seller Details, User clone, Invoice clone)




Chain Of Responsibilities DP
Post payment steps have multiple handlers
(Order Handler,
Update book Handler
AddBookToBuyer Handler
Add book to Seller Handler
Create Invoice Handler
Add Invoice to Buyer Handler
Add Invoice to Seller Handler
ProcessingChain Lines 57-67)
Iterator DP
Simple Search(Search by Query Lines 64-76)
Advanced Search (Separately by
title lines 108-122,
author lines 93-106,
genre lines 78-91)

Strategy with Factory DP
Payment Mode Strategy (Payment Strategy,  PaymentStrategyFactory, PaymentProcessor, NetBankingPayment, UPIPayment, CreditCardPayment)

Template DP
Processing Payment steps using template (PaymentStrategy. processPayment lines 5 - 9)

Adapter DP
Converting data into ui components (Books Adapter, Users Adapter, Invoices Adapter)

Facade DP
Communicated with facades of external SDKs like
Picasso Line 84,
Firebase Firestore in all Network classes UserNetwork, InvoiceNetwork, BookNetwork, SearchBooksNetwork,
Firebase storage Upload PDFs Line 63
Gson Logging data queried from db Eg : Current user Line 48

