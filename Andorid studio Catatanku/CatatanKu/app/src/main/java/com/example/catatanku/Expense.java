package com.example.catatanku;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Expense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner spin;
    private String[] kategori = {"Makanan", "Minuman", "Pakaian", "Utilitas", "Transportasi", "Hiburan"
            , "Asuransi", "Edukasi", "Lainnya"};
    private EditText dateText, currency, jumlah, tgl;
    private Button input;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    Integer updateID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        jumlah = findViewById(R.id.edtJumlah);
        tgl = findViewById(R.id.edtDate);
        spin = findViewById(R.id.spinner);

        dateText = findViewById(R.id.edtDate);

        input = (Button) findViewById(R.id.btnInput);

        currency = findViewById(R.id.edtJumlah);
        currency.addTextChangedListener(tw);

        generatedView();

        findViewById(R.id.btnDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }

            private void showDatePickerDialog() {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Expense.this,
                        Expense.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        Button back;
        back = (Button) findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spin = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategori);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Jumlah = jumlah.getText().toString();
                final String tanggal = tgl.getText().toString();
                String spinner = spin.getSelectedItem().toString();

                AlertDialog.Builder dialog = new AlertDialog.Builder(Expense.this);
                dialog.setTitle("Data Input");
                dialog.setMessage("Apakah data yang dimasukan sudah benar?\n " + "\nKategori : " + spinner
                        + "\n Jumlah : Rp." + Jumlah + "\n Tanggal : " + tanggal);
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (input.getText().toString().equals("UPDATE")) {
                            Log.d("aim","update");
                            updateExpense(updateID,spin.getSelectedItem().toString(),Jumlah,tanggal);
                        } else {
                            Log.d("aim","insert");
                            insertExpense(spin.getSelectedItem().toString(),Jumlah,tanggal);
                        }


                    }
                });
                dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }

    private void generatedView() {
        Intent intent = getIntent();

        if (intent.hasExtra("id")) {
            // Update
            input.setText("UPDATE");
            updateID = intent.getIntExtra("id",0);
            tgl.setText(intent.getStringExtra("tanggal"));
            jumlah.setText(intent.getStringExtra("jumlah"));

            String restoreKategori = intent.getStringExtra("kategori");
            for (int i = 0; i <= kategori.length-1; i++) {
                if (kategori[i].equals(restoreKategori)) {
                    spin.setSelection(i);
                }
            }
        } else {
            input.setText("INSERT");
        }


    }

    private void insertExpense(String kategori, String jumlah, String tanggal) {
        jumlah = jumlah.replace(",","");
        Log.d("aim","jumlah : "+jumlah);
        String url = "http://"+EndPoint.BASE_URL+"/catatan/add_expense.php?kategori="+kategori+"&jumlah="+jumlah+"&tanggal="+tanggal;

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String pesan = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();
                    finish();
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

    private void updateExpense(Integer id, String kategori, String jumlah, String tanggal) {
        jumlah = jumlah.replace(",","");
        Log.d("aim","jumlah : "+jumlah);
        String url = "http://"+EndPoint.BASE_URL+"/catatan/update_expense.php?id="+id+"&kategori="+kategori+"&jumlah="+jumlah+"&tanggal="+tanggal;

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String pesan = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();
                    finish();
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

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu, menu);
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:

                String kategori = spin.getSelectedItem().toString().trim();
                String Jumlah = jumlah.getText().toString().trim();
                String date = tgl.getText().toString().trim();

                if (date.isEmpty()){
                    tgl.setError("Masukan Tanggal");
                }else if (Jumlah.isEmpty()){
                    jumlah.setError("Masukan Jumlah");
                }else {
                    saveNote(date, Jumlah, kategori);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote(final String kategori, final String Jumlah, final String date) {
       progressDialog.show();

       apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(kategori,Jumlah,date);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success){
                        Toast.makeText(Expense.this,
                                response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(Expense.this,
                                response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                Toast.makeText(Expense.this,
                       t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateText = findViewById(R.id.edtDate);
        String dateSelected = year + "-" + month  + "-" + dayOfMonth;
        dateText.setText(dateSelected);

    }


    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            currency.removeTextChangedListener(this);

            try {
                String originalString = s.toString();
                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);

                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);

                currency.setText(formattedString);
                currency.setSelection(currency.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

            currency.addTextChangedListener(this);

        }

        ;
    };
}

