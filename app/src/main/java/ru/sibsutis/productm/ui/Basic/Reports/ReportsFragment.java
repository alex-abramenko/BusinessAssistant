package ru.sibsutis.productm.ui.Basic.Reports;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ui.Basic.Reports.DetailReport.DetailReportActivity;

public class ReportsFragment extends Fragment {

    private Spinner spinner;
    private EditText eTxt_StartDate;
    private EditText eTxt_EndDate;
    private Button btn_go;

    private Context context;

    public static final String[] CH_SPIN = {"Продажи", "Прочие расходы", "Товар-закуп", "Общий отчет"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reports, container, false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().findViewById(R.id.navigation_products).setClickable(true);
        getActivity().findViewById(R.id.navigation_adding).setClickable(true);
        getActivity().findViewById(R.id.navigation_reports).setClickable(false);

        context = getActivity();

        spinner = getActivity().findViewById(R.id.spinner_reports);
        eTxt_StartDate = getActivity().findViewById(R.id.eTxt_reports_StartDate);
        eTxt_EndDate = getActivity().findViewById(R.id.eTxt_reports_EndDate);
        btn_go = getActivity().findViewById(R.id.btn_reports_go);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, CH_SPIN);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(0);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());

        eTxt_EndDate.setText(simpleDateFormat.format(calendar.getTime()));

        calendar.add(Calendar.MONTH, -1);

        eTxt_StartDate.setText(simpleDateFormat.format(calendar.getTime()));

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDetailReportActivity();
            }
        });
    }

    private void toDetailReportActivity() {
        Intent intent = new Intent(context, DetailReportActivity.class);
        intent.putExtra("StartDate", eTxt_StartDate.getText().toString() + ", 00:01");
        intent.putExtra("EndDate", eTxt_EndDate.getText().toString() + ", 23:59");
        intent.putExtra("Choice", spinner.getSelectedItem().toString());
        startActivity(intent);
    }
}
