package ru.sibsutis.productm.ui.Basic.Reports.DetailReport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;


class TradesListAdapter extends ArrayAdapter<Trade> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Trade> trades;

    public TradesListAdapter(Context context, int resource, ArrayList<Trade> trades) {
        super(context, resource, trades);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.trades = trades;
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

        final Trade trade = trades.get(position);

        viewHolder.tV_date.setText(MiniProductAccounting.parseLongTimeToStrDate(trade.Date));
        viewHolder.tV_tittle.setText(trade.Tittle_Product);
        viewHolder.tV_quantity.setText(String.valueOf(trade.Quantity) + " шт.");
        viewHolder.tV_price.setText(String.valueOf(trade.Price) + " руб.");

        return convertView;
    }

    private class ViewHolder {
        TextView tV_date, tV_tittle, tV_quantity, tV_price;

        ViewHolder(View view){
            tV_date = view.findViewById(R.id.tV_tradeItem_date);
            tV_tittle = view.findViewById(R.id.tV_tradeItem_tittle);
            tV_quantity = view.findViewById(R.id.tV_tradeItem_quantity);
            tV_price = view.findViewById(R.id.tV_tradeItem_price);
        }
    }
}