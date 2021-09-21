package com.example.catatanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catatanku.adapter.ExpenseAdapter;
import com.example.catatanku.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Catatan_Expense extends AppCompatActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private List<DataModel> mDataModel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan__expense);

        Button back = (Button)findViewById(R.id.btn_back);
        Button input = (Button)findViewById(R.id.btn_input);

        // Recycler view
        mList = findViewById(R.id.recycler_expense);
        mDataModel = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        getExpense();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Catatan_Expense.this, Expense.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataModel.clear();
        getExpense();
    }

    private void getExpense() {
        String url = "http://"+EndPoint.BASE_URL+"/catatan/get_expense.php";

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray dataArray = new JSONArray(response);

                    for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject jsonObject = dataArray.getJSONObject(i);

                            DataModel model = new DataModel();
                            model.setId(jsonObject.getInt("id_expense"));
                            model.setKategori(jsonObject.getString("kategori"));
                            model.setJumlah(jsonObject.getString("jumlah"));
                            model.setTanggal(jsonObject.getString("tanggal"));

                            mDataModel.add(model);
                    }

                    adapter = new ExpenseAdapter(getApplicationContext(), mDataModel);

                    mList.setHasFixedSize(true);
                    mList.setLayoutManager(linearLayoutManager);
                    mList.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "err :" + e, Toast.LENGTH_LONG).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String test = String.valueOf(error.networkResponse);
                Toast.makeText(getApplicationContext(), "err Response :" + test, Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}
