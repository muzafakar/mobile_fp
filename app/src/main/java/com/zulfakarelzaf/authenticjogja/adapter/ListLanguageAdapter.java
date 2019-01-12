package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Languages;
import com.zulfakarelzaf.authenticjogja.R;

import java.util.List;

public class ListLanguageAdapter extends RecyclerView.Adapter<ListLanguageAdapter.Holder> {
    Context context;
    List<Languages> data;
    TableLayout mTableLayout;
    CategoryLanguages categoryLanguages;

    public ListLanguageAdapter(Context context, List<Languages> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_language2, parent, false);
        mTableLayout = v.findViewById(R.id.tableLanguages);
        loadData();
        startLoadData();
        return new ListLanguageAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
//        holder.number.setText(String.valueOf(position + 1));
//        holder.java.setText("-" + data.get(position).getJavanese());
//        holder.id.setText("-" + data.get(position).getIndonesia());
//        holder.us.setText(data.get(position).getUs());

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class Holder extends RecyclerView.ViewHolder {
//        TextView number, id, us, java;

        public Holder(View itemView) {
            super(itemView);
//            us = itemView.findViewById(R.id.textUs);
//            number = itemView.findViewById(R.id.textNumber);
//            id = itemView.findViewById(R.id.textid);
//            java = itemView.findViewById(R.id.textJavanese);
        }
    }

    public void startLoadData() {
        new LoadDataTask().execute(0);
    }

    public void loadData() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        int width = displayMetrics.widthPixels;

        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;
        int id=0; String us=""; String indonesia=""; String javanese="";

        Languages languages = new Languages(id,us,indonesia,javanese,categoryLanguages);
        Languages[] datas = new Languages[data.size()];

        int rows = data.size();
        TextView textSpacer = null;

        mTableLayout.removeAllViews();

        // -1 means heading row
        for (
                int i = -1;
                i < rows; i++)

        {
            Languages row = null;
            if (i > -1)
                row = datas[i];
            else {
                textSpacer = new TextView(context);
                textSpacer.setText("");

            }
            // data columns

            final TextView tv2 = new TextView(context);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);

            }

            tv2.setGravity(Gravity.TOP);

            tv2.setPadding(5, 5, 0, 5);
            if (i == -1) {
                tv2.setText("English");
                tv2.setBackgroundColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#ffffff"));
            } else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(data.get(i).getUs());
                //tv2.setText("halloo haii halloo");
                tv2.setMaxLines(10);
                tv2.setMaxWidth(width/3);
            }

            final TextView tv3 = new TextView(context);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);

            }

            tv3.setGravity(Gravity.TOP);

            tv3.setPadding(5, 5, 0, 5);
            if (i == -1) {
                tv3.setText("Indonesian");
                tv3.setBackgroundColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#ffffff"));
            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setText(data.get(i).getIndonesia());
                //tv3.setText("halloo haii halloo");
                tv3.setMaxLines(10);
                tv3.setMaxWidth(width/3);
            }

            final TextView tv4 = new TextView(context);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);

            }

            tv4.setGravity(Gravity.TOP);

            tv4.setPadding(5, 5, 0, 5);
            if (i == -1) {
                tv4.setText("Javanese");
                tv4.setBackgroundColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#ffffff"));
            } else {
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setText(data.get(i).getJavanese());
                //tv4.setText("halloo haii halloo");
                tv4.setMaxLines(10);
                tv4.setMaxWidth(width/3);
            }
//
//            // add table row
            final TableRow tr = new TableRow(context);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0, 0, 0, 0);
            tr.setLayoutParams(trParams);


            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);


            if (i > -1) {

                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        //do whatever action is needed

                    }
                });
            }
            mTableLayout.addView(tr, trParams);

            if (i > -1) {

                // add separator row
                final TableRow trSep = new TableRow(context);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(context);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 3;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);

                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }
        }
    }

        class LoadDataTask extends AsyncTask<Integer, Integer, String> {
            @Override
            protected String doInBackground(Integer... params) {

                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Task Completed.";
            }

            @Override
            protected void onPostExecute(String result) {
                loadData();
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

            }
        }

    }






