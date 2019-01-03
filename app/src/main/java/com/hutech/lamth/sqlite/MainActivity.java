package com.hutech.lamth.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Students> studentsList = new ArrayList<Students>();
    StudentAdapter studentAdapter = null;
    Cursor cursor = null;

    SQLiteCmd helper;

    ListView lsvStudents;
    EditText edtName,edtAddress;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteCmd(this);

        helper.QueryData("CREATE TABLE IF NOT EXISTS student(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), address VARCHAR(100))");

        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);
        lsvStudents = findViewById(R.id.lvStudents);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Students students = new Students();
                students.setName(edtName.getText().toString());
                students.setAddress(edtAddress.getText().toString());

                helper.insert(students.getName(),students.getAddress());
                   cursor.requery();
            }
        });

        cursor = helper.getAll();
        startManagingCursor(cursor);
        studentAdapter = new StudentAdapter(MainActivity.this,cursor);
        lsvStudents.setAdapter(studentAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
