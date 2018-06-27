package com.example.onurs.donanmhaber;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter{

    Context context;
    ArrayList<ItemData> dataArrayList;
    LayoutInflater layoutInflater;

    public ItemAdapter(Activity activity, ArrayList<ItemData> dataArrayList){
        this.context = activity;
        this.dataArrayList = dataArrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public ItemData getItem(int position) {
        return dataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_dh,null);

        LinearLayout ll = view.findViewById(R.id.ll);
        ImageView ivImage = view.findViewById(R.id.ivImage);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvTotalRead = view.findViewById(R.id.tvTotalRead);

        tvTitle.setText(dataArrayList.get(position).getDataTitle());
        tvTotalRead.setText(String.valueOf(dataArrayList.get(position).getTotalRead()));
        ll.setBackgroundColor(Color.parseColor(dataArrayList.get(position).getColorAvarage()));
        tvTitle.setTextColor(Color.parseColor(dataArrayList.get(position).getTextColor()));

        new DownloadImageTask(ivImage).execute(dataArrayList.get(position).getImageValue());

        return view;
    }
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
