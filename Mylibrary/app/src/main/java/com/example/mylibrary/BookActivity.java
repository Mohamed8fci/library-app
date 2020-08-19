package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "BookActivity";
    private TextView bookName,authorName,description,pageNumber;
    private ImageView bookImage;
    private Button btnCurReading, btnWantToRead, btnAlreadyRead;
    private book incomingBook;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();
        Intent intent =getIntent();
        int id = intent.getIntExtra("bookId",0);
        util =new Util();
        ArrayList<book> books= util.getAllBooks();
        for (book b: books){
            if(b.getId()==id){
                incomingBook=b;
                bookName.setText(b.getName());
                authorName.setText(b.getAuthor());
                description.setText(b.getDescription());
                pageNumber.setText("Pages: "+ b.getPages());

                Glide.with(this)
                        .asBitmap()
                        .load(b.getImageUrl())
                        .into(bookImage);
            }
        }
        btnCurReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCurReadingTapped();
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAlreadyReadTapped();
            }
        });
        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWantToReadTapped();
            }
        });
    }
    private void btnWantToReadTapped(){
        Log.d(TAG, "btnWantToReadTapped: started");
        ArrayList<book> wantToReadBooks = util.getWantToReadAllBooks();

        if (wantToReadBooks.contains(incomingBook)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You Already Added this Book to your Want to Read  Books");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();
        }else {
            util.addWantToReadBooks(incomingBook);
            Toast.makeText(this,"The Book"+incomingBook.getName() + "Added to your Want to read Shelf",Toast.LENGTH_LONG).show();
        }
    }
    private void btnAlreadyReadTapped(){
        Log.d(TAG, "btnAlreadyReadTapped: started");
        Boolean doesExist=false;
        ArrayList<book> alreadyReadBooks = util.getAlreadyReadBooks();
        for (book book: alreadyReadBooks){
            if (book.getId() == incomingBook.getId()){
                doesExist=true;
            }
        }
        if (doesExist){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You Already Added this Book to your Already Read Books");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();
        }else {
            util.addAlreadyToReadBook(incomingBook);
            Toast.makeText(this,"The Book"+incomingBook.getName() + "Added to your Already Read Shelf",Toast.LENGTH_LONG).show();
        }
    }

    private void btnCurReadingTapped(){
        Log.d(TAG, "btnCurReadingTapped: started");
        Boolean doesExist=false;
        ArrayList<book> CurrentlyReadingBooks = util.getCurrentlyReadingBooks();
        for (book book: CurrentlyReadingBooks){
            if (book.getId() == incomingBook.getId()){
                doesExist=true;
            }
        }
        if (doesExist){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You Already Added this Book to your currently Reading Books");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();
        }else {
            util.addCurrentlyReadingBooks(incomingBook);
            Toast.makeText(this,"The Book"+incomingBook.getName() + "Added to your Currently Reading Shelf",Toast.LENGTH_LONG).show();
        }
    }

    private void initWidgets(){
        bookName =(TextView) findViewById(R.id.bookName);
        authorName =(TextView) findViewById(R.id.authorName);
        description =(TextView) findViewById(R.id.bookDesc);
        pageNumber = (TextView) findViewById(R.id.bookPages);

        bookImage =(ImageView) findViewById(R.id.bookImage);

        btnAlreadyRead = (Button) findViewById(R.id.btnAlreadyRead);
        btnWantToRead = (Button) findViewById(R.id.btnWantToRead);
        btnCurReading = (Button) findViewById(R.id.btnCurReading);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
            super.onBackPressed();
            break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}