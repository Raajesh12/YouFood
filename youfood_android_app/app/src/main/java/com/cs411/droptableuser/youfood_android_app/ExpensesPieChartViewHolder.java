package com.cs411.droptableuser.youfood_android_app;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;

import com.cs411.droptableuser.youfood_android_app.responses.GETTransactionResponse;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunYoung on 2018. 4. 13..
 */

public class ExpensesPieChartViewHolder extends BaseViewHolder {
    private static final String TAG = "ExpensesPieChartViewHolder";

    @BindView(R.id.piechart_expenses)
    PieChart pieChartExpenses;

    public ExpensesPieChartViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ArrayList<GETTransactionResponse> weeklyTransactions) {
        List<PieEntry> entries = new ArrayList<>();

        pieChartExpenses.getDescription().setEnabled(false);
        pieChartExpenses.setExtraOffsets(0f, -30f, 0f, -60f);

        if (weeklyTransactions.size() != 0) {
            HashMap<String, Float> transactions = categorizeAmountByDate(weeklyTransactions);
            int firstDayOfWeek = Integer.valueOf(DateTime.getFirstDateOfWeek(
                    weeklyTransactions.get(0).getDate()).substring(0, 2));
            addEntryForPieData(transactions, firstDayOfWeek, entries);
        }

        PieDataSet set = new PieDataSet(entries, "");
        set.setValueTextSize(23f);
        List<Integer> pieColors = setPieChartColor();
        set.setColors(pieColors);
        List<Integer> valueTestColors = setValueTextColors();
        set.setValueTextColors(valueTestColors);
        set.setValueFormatter(new DollarSignFormatter());

        PieData data = new PieData(set);

        customizeLegend();
        pieChartExpenses.setCenterText("Expenses Chart");
        pieChartExpenses.setDrawEntryLabels(true);
        pieChartExpenses.setDrawCenterText(true);
        pieChartExpenses.setEntryLabelTextSize(12f);
        pieChartExpenses.setEntryLabelColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
        pieChartExpenses.animateX(1500);
        pieChartExpenses.setData(data);
        pieChartExpenses.invalidate();
    }

    private void customizeLegend() {
        Legend legend = pieChartExpenses.getLegend();

        legend.setYOffset(16f);
        legend.setFormSize(30f);
        legend.setXEntrySpace(20f);
        legend.setYEntrySpace(10f);
        legend.setWordWrapEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTypeface(ResourcesCompat.getFont(itemView.getContext(), R.font.roboto));
    }

    private List<Integer> setPieChartColor() {
        Context context = itemView.getContext();
        List<Integer> pieColors = new ArrayList<>();

        pieColors.add(ContextCompat.getColor(context, R.color.Red1));
        pieColors.add(ContextCompat.getColor(context, R.color.Red2));
        pieColors.add(ContextCompat.getColor(context, R.color.Red3));
        pieColors.add(ContextCompat.getColor(context, R.color.Red4));
        pieColors.add(ContextCompat.getColor(context, R.color.Red5));
        pieColors.add(ContextCompat.getColor(context, R.color.Red6));
        pieColors.add(ContextCompat.getColor(context, R.color.Red7));

        return pieColors;
    }

    private List<Integer> setValueTextColors() {
        Context context = itemView.getContext();
        List<Integer> valueTextColors = new ArrayList<>();

        valueTextColors.add(ContextCompat.getColor(context, R.color.white));
        valueTextColors.add(ContextCompat.getColor(context, R.color.white));
        valueTextColors.add(ContextCompat.getColor(context, R.color.white));
        valueTextColors.add(ContextCompat.getColor(context, R.color.black));
        valueTextColors.add(ContextCompat.getColor(context, R.color.black));
        valueTextColors.add(ContextCompat.getColor(context, R.color.black));
        valueTextColors.add(ContextCompat.getColor(context, R.color.black));

        return valueTextColors;
    }

    private HashMap<String, Float> categorizeAmountByDate(
            ArrayList<GETTransactionResponse> transactionList) {
        HashMap<String, Float> map = new HashMap<>();
        for (GETTransactionResponse transaction : transactionList) {
            String key = transaction.getDate().substring(0, 2);
            if (map.containsKey(key)) {
                float currentAmount = map.get(key);
                currentAmount += Float.valueOf(transaction.getAmount().substring(1));

                map.put(key, currentAmount);
            } else {
                map.put(key, Float.valueOf(transaction.getAmount().substring(1)));
            }
        }

        return map;
    }

    private void addEntryForPieData(HashMap<String, Float> transactions, int firstDayOfWeek,
                                    List<PieEntry> entries) {
        for (int i = 1; i <= Calendar.DAY_OF_WEEK; i++) {
            String key = String.valueOf(firstDayOfWeek + (i - 1));
            if (transactions.containsKey(key)) {
                if (i == Calendar.SUNDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Sunday"));
                } else if (i == Calendar.MONDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Monday"));
                } else if (i == Calendar.TUESDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Tuesday"));
                } else if (i == Calendar.WEDNESDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Wednesday"));
                } else if (i == Calendar.THURSDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Thursday"));
                } else if (i == Calendar.FRIDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Friday"));
                } else if (i == Calendar.SATURDAY) {
                    entries.add(new PieEntry(transactions.get(key), "Saturday"));
                }
            }
        }
    }
}
