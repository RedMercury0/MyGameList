package com.dorontayar_nirtzameret.mygameslist.main.previewActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;

import java.util.Objects;

public class PreviewGameActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private ImageView backImage;
    private VideoView clipGame;
    private ViewPager2 viewPager;
    private RecyclerView genresGame;
    private RelativeLayout cardstates;
    private Toolbar toolbar;
    private TextView titleGameToolbar;
    private InfoGame infoGame;
    private Gson gson;
    private String gameData;
    private String gameID;
    private String gameNAME;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_game);

        // Initialize views
        initViews();

        // Get intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameData = extras.getString("GameData");
            gameID = extras.getString("game_id");
            gameNAME = extras.getString("game_name");

            // Retrieve JSON string from the intent
            String infoGameJson = getIntent().getStringExtra("infoGameJson");
            // Deserialize JSON string back into InfoGame object
            infoGame = new Gson().fromJson(infoGameJson, InfoGame.class);

        }

        // Initialize Gson
        // gson = new Gson();



        // Setup UI components and listeners
        setupUI();
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appbarlayout);
        clipGame = findViewById(R.id.clipGame);
        viewPager = findViewById(R.id.viewPager);
        genresGame = findViewById(R.id.genresGame);
        cardstates = findViewById(R.id.cardstates);
        toolbar = findViewById(R.id.toolbar);
        backImage = findViewById(R.id.back);
        titleGameToolbar = findViewById(R.id.titleGameToolbar);
    }

    private void setupUI() {
        //setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // Set navigation icon color filter
        setNavigationIconColorFilter();

        // Setup app bar layout listener
        setupAppBarLayoutListener();

        // Set up views with data
        titleGameToolbar.setText(infoGame.getName());
    }

    private void setNavigationIconColorFilter() {
        Drawable navIcon = toolbar.getNavigationIcon();
        if (navIcon != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                navIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            } else {
                navIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    private void setupAppBarLayoutListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                // Handle app bar layout state changes
                handleAppBarLayoutState(verticalOffset);
            }
        });
    }

    private void handleAppBarLayoutState(int verticalOffset) {
        // Handle app bar layout state changes
    }



}
