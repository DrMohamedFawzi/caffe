package com.msnit.localcafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.msnit.localcafe.cafe.CafeManagement;
import com.msnit.localcafe.cafe.CafeRequest;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private final Context context;


    public OrdersAdapter(Context context) {
        this.context = context;
    }


    private void addEventRemove(int tableNum, AppCompatButton button) {
        button.setOnClickListener(v -> CafeManagement.remove(tableNum));
    }

    @NonNull
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new OrdersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.MyViewHolder holder, int position) {
        CafeRequest request = CafeManagement.requests.get(position);

        holder.tableNumView.setText(Integer.toString(request.getTableNum()));

        String[] order = request.getCups();

        holder.ordersView.setText(order[0]);
        holder.numbersView.setText(order[1]);

        addEventRemove(request.getTableNum(), holder.removeBtn);

    }

    @Override
    public int getItemCount() {
        return CafeManagement.requests.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tableNumView, ordersView, numbersView;
        AppCompatButton removeBtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tableNumView = itemView.findViewById(R.id.table_number);
            ordersView = itemView.findViewById(R.id.orders);
            numbersView = itemView.findViewById(R.id.numbers);
            removeBtn = itemView.findViewById(R.id.remove);
        }
    }
}
