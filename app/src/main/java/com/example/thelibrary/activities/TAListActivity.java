package com.example.thelibrary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.thelibrary.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TAListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_a_list);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("orders");
        ref.orderByChild("complete").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {

            ScrollView list = (ScrollView) findViewById(R.id.orders);
            String orderID;

            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    if (orderSnapshot.child("collect").equals("איסוף עצמי") && orderSnapshot.child("arrivedToUser").equals(false)) {
                        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayoutTA);
                        TextView tv = new TextView(TAListActivity.this);
                        orderID = orderSnapshot.getKey();
                        tv.setText(orderID);
                        ll.addView(tv);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}