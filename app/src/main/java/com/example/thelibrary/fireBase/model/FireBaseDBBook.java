package com.example.thelibrary.fireBase.model;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thelibrary.fireBase.model.dataObj.BookObj;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class FireBaseDBBook extends FireBaseModel {
    public void addBookToDB(String bookName, String author, String brief, String genre, String language, String publishing_year, AppCompatActivity activity){

        writeNewBook(bookName, author, brief, genre, language, publishing_year, activity);
    }
    public void writeNewBook(String bookName,String author,String brief,String genre,String language, String publishing_year, AppCompatActivity activity){
        BookObj book = new BookObj(bookName, author, brief, genre, language, publishing_year);
        myRef=myRef.child("books");
        String keyId= myRef.push().getKey();
        myRef.child(keyId).setValue(book, new DatabaseReference.CompletionListener(){

        @Override
        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
            if (databaseError != null) {
                Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, "Saved!!", Toast.LENGTH_LONG).show();
            }
        }
    });
    }
    public DatabaseReference getBookFromDB(String bookID){
        return myRef.getRef().child("book").child(bookID);
    }
}