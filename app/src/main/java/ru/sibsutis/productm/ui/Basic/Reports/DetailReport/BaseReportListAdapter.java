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
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.ReportItem;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;


class BaseReportListAdapter extends ArrayAdapter<ReportItem> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<ReportItem> reportItems;

    public BaseReportListAdapter(Context context, int resource, ArrayList<ReportItem> repItem) {
        super(context, resource, repItem);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.reportItems = repItem;
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

        final ReportItem repItem = reportItems.get(position);

        viewHolder.tV_tittle.setText(repItem.tittleProduct);
        viewHolder.tV_quantity.setText(String.valueOf(repItem.quanProduct) + " шт.");
        viewHolder.tV_price.setText(String.valueOf(repItem.priceProduct) + " руб.");

        return convertView;
    }

    private class ViewHolder {
        TextView tV_tittle, tV_quantity, tV_price;

        ViewHolder(View view){
            tV_tittle = view.findViewById(R.id.tV_baseRepItem_tittle);
            tV_quantity = view.findViewById(R.id.tV_baseRepItem_quantity);
            tV_price = view.findViewById(R.id.tV_baseRepItem_price);
        }
    }
}