package com.qiang.alladapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ViewHolder  {

    private final SparseArray<View> mviews;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mviews = new SparseArray<>();
        mConvertView =LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
       if(convertView==null){
           return new ViewHolder(context,parent,layoutId,position);
       }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId) {
      View view =mviews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mviews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }


    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

}
