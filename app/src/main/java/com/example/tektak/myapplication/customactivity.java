package com.example.tektak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class customactivity extends PagerAdapter {

     Context context;
        int[] imageId = { R.drawable.foxcrow1, R.drawable.foxcrow2, R.drawable.foxcrow3, R.drawable.foxcrow4, R.drawable.foxcrow5, R.drawable.foxcrow6, R.drawable.foxcrow7, R.drawable.foxcrow8};
   //String[] stringarray = { "Image a", "Image b","Image c","Image d","Image e"};
        public customactivity(Context context){
            this.context = context;

        }
    @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View viewItem = inflater.inflate(R.layout.image_item, container, false);
            ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
            imageView.setImageResource(imageId[position]);

        //TextView txt=(TextView) viewItem.findViewById(R.id.textView);
        //txt.setText(stringarray[position]);



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

