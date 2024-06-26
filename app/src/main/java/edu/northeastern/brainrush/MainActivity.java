package edu.northeastern.brainrush;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import edu.northeastern.brainrush.model.User;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private Picasso picasso;
    private TextView score, level;
    private User user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define the text view
        score = findViewById(R.id.score);
        level = findViewById(R.id.level);

        //Set the user
        uid = "test2";
        user = new User("tester2");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Setting up the date selected from the previous page
            uid = extras.getString("id");
            user = (User) extras.get("user");
        }

        score.setText("Score: " + user.getScore());
        level.setText("Level: " + user.getLevel());


        Log.v("user", user.getName());

        //Construct singleton instance for Picasso
        File httpCacheDirectory = new File(this.getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024); // 10 MiB

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttpClientBuilder.build());
        try {
            picasso = new Picasso.Builder(this)
                    .downloader(okHttp3Downloader)
                    .build();
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException e) {
            // Handle the exception if the singleton instance was already set
            Log.w("Picasso", "Picasso instance was already set. It's safe to ignore this.");
        }
    }

    public void openUserFile(View v){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    public void openMail(View v){
        Intent intent = new Intent(MainActivity.this, panelactivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    public void openDashboard(View v){
        Intent intent = new Intent(MainActivity.this, DailyQuestionsActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    public void openLearn(View v){
        Intent intent = new Intent(MainActivity.this, LearnActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    public void openMake(View v){
        Intent intent = new Intent(MainActivity.this, MakeActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

    public void openCompete(View v){
        Intent intent = new Intent(MainActivity.this, CompeteActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("id", uid);
        startActivity(intent);
    }

}