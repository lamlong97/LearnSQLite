package com.hutech.lamth.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class StudentAdapter extends CursorAdapter {


    public StudentAdapter(Context context, Cursor c) {
        super(context, c);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.custom_row,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        SQLiteCmd helper = new SQLiteCmd(context);
        TextView txtName,txtAddress;
        txtName = view.findViewById(R.id.txtName);
        txtAddress = view.findViewById(R.id.txtAddress);

        txtName.setText(helper.getName(cursor));
        txtAddress.setText(helper.getAddress(cursor));
    }
}
