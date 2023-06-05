package com.example.car_founder_final;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class CarItemAdapter extends ArrayAdapter<CarItem> {

    public CarItemAdapter (Context context, List<CarItem> carItems) {
        super(context, 0, carItems);
    }
    private static class ViewHolder{
        ImageView imageitem;
        TextView textView;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        CarItem carItem = getItem(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_item,parent,false);
            viewHolder.textView = convertView.findViewById(R.id.tv);
            viewHolder.imageitem = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();


        }
        viewHolder.textView.setText(carItem.getText());
        viewHolder.imageitem.setImageResource(carItem.getImage());
        return convertView;


    }

    private int getImageResourceIdFromBitmap(Bitmap bitmap) {
        // Convert the Bitmap to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // Convert byte array to Drawable
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
        Drawable drawable = Drawable.createFromStream(inputStream, "image_profile");

        // Get the resource ID from the Drawable
        Resources resources = getContext().getResources();
        String packageName = getContext().getPackageName();
        int imageResourceId = resources.getIdentifier("image_profile", "drawable", packageName);

        return imageResourceId;
    }



}
