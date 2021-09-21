package com.example.catatanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catatanku.EndPoint;
import com.example.catatanku.Expense;
import com.example.catatanku.R;
import com.example.catatanku.model.DataModel;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> list;


    public ExpenseAdapter(Context context, List<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listexpense, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DataModel data = list.get(position);


        holder.textKategori.setText(data.kategori);
        holder.textJumlah.setText(data.jumlah);
        holder.textTanggal.setText(data.tanggal);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Expense.class);
                i.putExtra("id",data.id);
                i.putExtra("kategori",data.kategori);
                i.putExtra("jumlah",data.jumlah);
                i.putExtra("tanggal",data.tanggal);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(data);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textKategori, textJumlah, textTanggal;
        Button btnEdit, btnDelete;

        ViewHolder(View itemView) {
            super(itemView);

            textKategori = itemView.findViewById(R.id.kategori);
            textJumlah = itemView.findViewById(R.id.jumlah);
            textTanggal = itemView.findViewById(R.id.tanggal);

            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    private void deleteData(final DataModel data) {
        String url = "http://"+ EndPoint.BASE_URL+"/catatan/delete_expense.php?id="+data.id;

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                list.remove(data);
                notifyDataSetChanged();
                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String test = String.valueOf(error.networkResponse);
                Toast.makeText(context, "err Response :" + test, Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}
