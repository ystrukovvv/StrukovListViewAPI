package com.example.strukov;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<User> mUserList = new ArrayList<>();
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonGet);
        ListView listView = findViewById( R.id.ListView );

        mAdapter = new Adapter(MainActivity.this, mUserList);
        listView.setAdapter(mAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute();
            }
        });
    }

    public class getData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String result = SyncManager.GetInstance().getData("users");
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.isEmpty()) {

                try {
                    JSONArray tempArray = new JSONArray(s);
                    for (int i = 0; i < tempArray.length(); i++) {
                        JSONObject tempO = tempArray.getJSONObject(i);
                        User tempUser = new User(tempO.getString("login"),
                                tempO.getString("avatar_url"), tempO.getString("type"));
                        mUserList.add(tempUser);
                        mAdapter.notifyDataSetChanged();
                        new DownloadAvatarUser(tempUser).execute();
                    }


                } catch (Exception e) {

                }
            }
        }

    }
    private class DownloadAvatarUser extends AsyncTask<Void, Void, Bitmap>
    {
        private User mUser;

        private DownloadAvatarUser(User mUser)
        {
            this.mUser = mUser;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return SyncManager.GetInstance().DownloadImage(mUser.getAvatar_url());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null)
            {
                mUser.setAvatar(bitmap);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
