package com.misis.chili.healthcare;

import android.content.Context;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chili on 15.11.2017.
 */

public class ListAdapter extends BaseAdapter {
    Context c;
    ArrayList<Card> cards;
    public ListAdapter(Context c, ArrayList<Card> cards) {
        this.c = c;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }
    @Override
    public Object getItem(int i) {
        return cards.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.cards,viewGroup,false);
        }
        final Card s= (Card) this.getItem(i);
        ImageView img= (ImageView) view.findViewById(R.id.person_photo);
        TextView nameTxt= (TextView) view.findViewById(R.id.person_name);
        TextView statTxt= (TextView) view.findViewById(R.id.person_status);
        nameTxt.setText(s.getName());
        statTxt.setText(s.getStatus());
        img.setImageResource(s.getImage());
        
        return view;
    }
}
