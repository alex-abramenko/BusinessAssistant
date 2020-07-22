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
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.PurchProducts;


class PurchprListAdapter extends ArrayAdapter<PurchProducts> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<PurchProducts> purchProducts;

    public PurchprListAdapter(Context context, int resource, ArrayList<PurchProducts> purchPr) {
        super(context, resource, purchPr);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.purchProducts = purchPr;
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

        final PurchProducts purchpr = purchProducts.get(position);

        viewHolder.tV_date.setText(MiniProductAccounting.parseLongTimeToStrDate(purchpr.Date));
        viewHolder.tV_tittle.setText(purchpr.Tittle_Product);
        viewHolder.tV_quantity.setText(String.valueOf(purchpr.Quantity) + " шт.");
        viewHolder.tV_price.setText(String.valueOf(purchpr.Price) + " руб.");

        return convertView;
    }

    private class ViewHolder {
        TextView tV_date, tV_tittle, tV_quantity, tV_price;

        ViewHolder(View view){
            tV_date = view.findViewById(R.id.tV_purchprItem_date);
            tV_tittle = view.findViewById(R.id.tV_purchprItem_tittle);
            tV_quantity = view.findViewById(R.id.tV_purchprItem_quantity);
            tV_price = view.findViewById(R.id.tV_purchprItem_price);
        }
    }
}