package com.example.tektak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class customactivity2 extends PagerAdapter {


    Context context;
    int[] imageId = { R.drawable.mky1, R.drawable.mky2, R.drawable.mky3, R.drawable.mky4, R.drawable.mky5, R.drawable.mky6, R.drawable.mky7, R.drawable.mky8, R.drawable.mky9, R.drawable.mky10};

    public customactivity2(Context context){
        this.context = context;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.imageitem2, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView3);
        imageView.setImageResource(imageId[position]);
        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub

        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }


}

