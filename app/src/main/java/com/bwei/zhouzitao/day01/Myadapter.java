package com.bwei.zhouzitao.day01;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 作者：周自涛
 * 日期：2018/10/6
 */
public class Myadapter extends BaseAdapter {



    private Context context;
    private List<Bean.DataBean> list;

    public Myadapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder = null;
        if (view==null){
            holder = new Holder();
            view = View.inflate(context, R.layout.show,null);
            holder.text_View1 = view.findViewById(R.id.textview1);
            holder.text_View2 = view.findViewById(R.id.textview2);
            holder.image_View = view.findViewById(R.id.imageview);


            view.setTag(holder);

        }else{
            holder = (Holder) view.getTag();

        }

        holder.text_View1.setText(list.get(i).getTitle());
        holder.text_View2.setText(list.get(i).getDate());
        Picasso.with(context).load(list.get(i).getThumbnail_pic_s02()).into(holder.image_View);
        return view;
    }
    class Holder{
        TextView text_View1;
        TextView text_View2;
        ImageView image_View;

    }
}
