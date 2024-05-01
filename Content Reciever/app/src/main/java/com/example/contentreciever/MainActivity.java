package com.example.contentreciever;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Uri CONTENT_URI = Uri.parse("content://com.demo.user.provider/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.loadButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShowDetails();
            }
        });
    }
    @SuppressLint("Range")
    public void onClickShowDetails() {
        // inserting complete table details in this text field
        TextView resultView = (TextView) findViewById(R.id.res);

        // creating a cursor object of the
        // content URI

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);
        Toast.makeText(this, ""+cursor, Toast.LENGTH_SHORT).show();
        // iteration of the cursor
        // to print whole table
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                StringBuilder strBuild = new StringBuilder();
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n").append(cursor.getString(cursor.getColumnIndex("id"))).append("-").append(cursor.getString(cursor.getColumnIndex("name")));
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            } else {
                resultView.setText("No Records Found");
            }
        }else {
            resultView.setText("No Records Found");
        }
    }

//    public void onClickShowDetails() {
//        TextView resultView = findViewById(R.id.res);
//
//        Cursor cursor = null;
//        try {
//            cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                StringBuilder strBuild=new StringBuilder();
//            while (!cursor.isAfterLast()) {
//                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
//                cursor.moveToNext();
//            }
//
//                resultView.setText(strBuild.toString());
//            } else {
//                resultView.setText("No Records Found");
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close(); // Close the cursor to release resources
//            }
//        }
//    }
}