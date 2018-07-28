package com.example.tektak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class customactivity1 extends PagerAdapter {


        Context context;
        int[] imageId = { R.drawable.lion1, R.drawable.lion2, R.drawable.lion3, R.drawable.lion4, R.drawable.lion5, R.drawable.lion6, R.drawable.lion7, R.drawable.lion8};

        public customactivity1(Context context){
            this.context = context;

        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            View viewItem = inflater.inflate(R.layout.image_item1, container, false);
            ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView1);
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

