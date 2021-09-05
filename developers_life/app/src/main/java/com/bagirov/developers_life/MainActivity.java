package com.bagirov.developers_life;

import android.os.Bundle;

import com.bagirov.developers_life.databinding.ActivityMainBinding;
import com.bagirov.developers_life.utils.JSONCollection;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//import com.bagirov.developers_life.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ArrayList<String> urls = new ArrayList<>();
        AtomicReference<String> imageURL = new AtomicReference<>("");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        updateImgViewerWithRemoteGif(imageURL, imageView, urls);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                updateImgViewerWithRemoteGif(imageURL, imageView, urls);
                System.out.println(urls.toString());

            }
        });


        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                updateImgViewerWithRemoteGif(imageURL, imageView, urls);
                System.out.println(urls.toString());

            }
        });
    }





        public void updateImgViewerWithRemoteGif(AtomicReference<String> imageURL, ImageView imageView, ArrayList<String> urls) {

        imageView.setImageResource(R.drawable.loading);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        imageURL.set(JSONCollection.getRandomGifLink());
                        urls.add(imageURL.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide
                                    .with(MainActivity.this)
                                    .asGif()
                                    .load(urls.get(urls.size() - 1)).error(R.drawable.err)
                                    .into(imageView)
                            ;
                        }
                    });

                }
            }).start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}