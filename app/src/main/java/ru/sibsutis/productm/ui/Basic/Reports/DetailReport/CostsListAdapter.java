package ru.sibsutis.productm.ui.Basic.Reports.DetailReport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Cost;


class CostsListAdapter extends ArrayAdapter<Cost> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Cost> costs;

    public CostsListAdapter(Context context, int resource, ArrayList<Cost> c) {
        super(context, resource, c);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.costs = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Cost cost = costs.get(position);

        viewHolder.tV_date.setText(MiniProductAccounting.parseLongTimeToStrDate(cost.Date));
        viewHolder.tV_price.setText(String.valueOf(cost.Price) + " руб.");
        viewHolder.tV_tittle.setText(cost.Tittle);

        return convertView;
    }

    private class ViewHolder {
        TextView tV_date, tV_price, tV_tittle;

        ViewHolder(View view){
            tV_date = view.findViewById(R.id.tV_costItem_date);
            tV_price = view.findViewById(R.id.tV_costItem_price);
            tV_tittle = view.findViewById(R.id.tV_costItem_tittle);

        }
    }
}