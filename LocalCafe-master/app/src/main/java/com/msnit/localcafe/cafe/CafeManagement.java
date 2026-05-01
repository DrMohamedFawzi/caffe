package com.msnit.localcafe.cafe;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import com.msnit.localcafe.OrdersAdapter;

import java.util.LinkedList;

public class CafeManagement {

    public static final LinkedList<CafeRequest> requests = new LinkedList<>();

    @SuppressLint("StaticFieldLeak")
    public static OrdersAdapter adapter;
    public static RecyclerView recyclerView;


    public static void updateCafe() {

    }

    public static void add(CafeRequest request) {
        int index = getIndex(request);

        if (index > -1) {
            requests.remove(request);
            recyclerView.post(() -> adapter.notifyItemRemoved(index));
        }

        requests.addFirst(request);
        recyclerView.post(() -> adapter.notifyItemInserted(0));

    }

    public static int getIndex(CafeRequest request) {
        for (int i = 0; i < requests.size(); i++) {
            if (request.equals(requests.get(i))) {
                return i;
            }
        }
        return -1;

    }

    public static boolean has(CafeRequest request) {
        for (CafeRequest theReq : requests)
            if (theReq.getTableNum() == request.getTableNum()) {
                return true;
            }
        return false;
    }

    public static boolean remove(int tableNum) {
        for (int i = 0, requestsSize = requests.size(); i < requestsSize; i++) {
            CafeRequest theReq = requests.get(i);
            if (theReq.getTableNum() == tableNum) {
                requests.remove(new CafeRequest(tableNum));
                final int finalI = i;
                recyclerView.post(() -> adapter.notifyItemRemoved(finalI));

                return true;
            }
        }
        return false;
    }

}
