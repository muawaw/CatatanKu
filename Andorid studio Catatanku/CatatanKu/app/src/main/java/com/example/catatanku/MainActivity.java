package com.example.catatanku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showIpAddressDialog();

        ImageButton income;
        income = (ImageButton)findViewById(R.id.btnIncome);
        ImageButton expense;
        expense = (ImageButton)findViewById(R.id.btnExpense);
        Button setting;
        setting = findViewById(R.id.btn_setting);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Budget.class);
//                startActivity(i);

                Intent i = new Intent(MainActivity.this, Catatan_Income.class);
                startActivity(i);

            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Expense.class);
//                startActivity(i);

                Intent i = new Intent(MainActivity.this, Catatan_Expense.class);
                startActivity(i);

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIpAddressDialog();
            }
        });

    }

    private void showIpAddressDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input IP Address");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setText(EndPoint.BASE_URL);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty()) {
                    EndPoint.BASE_URL = input.getText().toString();
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

}
