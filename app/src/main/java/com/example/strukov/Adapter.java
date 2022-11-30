package com.example.strukov;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context mContext;
    private List<User> mUserList;

    public Adapter(Context mContext, List<User> mUserList)
    {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mUserList.indexOf(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_user, null);


        TextView txt_log = v.findViewById(R.id.login);
        TextView txt_type = v.findViewById(R.id.type);
        ImageView imgAvatar = v.findViewById(R.id.avatar);


        User currUser = mUserList.get(i);
        txt_log.setText(currUser.getLogin());
        txt_type.setText(currUser.getType());


        if (currUser.getAvatar() != null)
            imgAvatar.setImageBitmap(currUser.getAvatar());

        return v;
    }
}
