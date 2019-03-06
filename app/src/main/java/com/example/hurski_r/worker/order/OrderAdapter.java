package com.example.hurski_r.worker.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hurski_r.worker.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Order> orders;

    public OrderAdapter(Context context, int resource, ArrayList<Order> orders) {
        super(context, resource, orders);
        this.orders = orders;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        //ImageView flagView = (ImageView) view.findViewById(R.id.flag);
        TextView numberView = (TextView) view.findViewById(R.id.numberOfOrder);
        TextView statusView = (TextView) view.findViewById(R.id.numberOfStatus);
        TextView adminView = (TextView) view.findViewById(R.id.numberOfAdmin);
        TextView dateView = (TextView) view.findViewById(R.id.numberOfData);
        TextView addressView = (TextView) view.findViewById(R.id.numberOfAddress);

        Order order = orders.get(position);


        numberView.setText(order.getNumber());
        statusView.setText(order.getStatus());
        adminView.setText(order.getAdmin());
        dateView.setText(order.getDate());
        addressView.setText(order.getAddress());

        return view;
    }
}