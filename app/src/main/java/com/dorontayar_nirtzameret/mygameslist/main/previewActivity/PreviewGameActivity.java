package com.dorontayar_nirtzameret.mygameslist.main.previewActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Genre;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.ParentPlatform;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.Publisher;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class PreviewGameActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private ImageView backImage;
    private VideoView clipGame;
    private ViewPager2 viewPager;
    private Toolbar toolbar;
    private TextView titleGameToolbar,titleGame,
            rateGameToolbar,rateGame,releaseDate,description,platforms,genres,publisher;

    private Button AddBookmark,RemoveBookmark;
    private InfoGame infoGame;

    private Gson gsonGame;
    private String gameData;
    private String gameID;
    private String gameNAME;
    private String user;
    private Boolean bookmarked = false;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_game);



        // Creating FireBase DatabaseReference to access firebase realtime database
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mygamelist-androidproject-default-rtdb.firebaseio.com/");

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

            // Retrieve the logged user name
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            user = prefs.getString("loggedInUser", null);
        }






        // Setup UI components and listeners
        setupUI();
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appbarlayout);
        clipGame = findViewById(R.id.clipGame);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
        backImage = findViewById(R.id.back);
        titleGameToolbar = findViewById(R.id.titleGameToolbar);
        titleGame = findViewById(R.id.titleGame);
        rateGameToolbar = findViewById(R.id.rateGameToolbar);
        rateGame = findViewById(R.id.rateGame);
        releaseDate = findViewById(R.id.releaseDate);
        description = findViewById(R.id.description);
        platforms = findViewById(R.id.platforms);
        genres = findViewById(R.id.genres);
        publisher= findViewById(R.id.publisher);
        AddBookmark = findViewById(R.id.btnAddBookmark);
        RemoveBookmark = findViewById(R.id.btnRemoveBookmark);
    }

    private void setupUI() {

        // Set navigation icon color filter
        setNavigationIconColorFilter();

        // Setup app bar layout listener
        setupAppBarLayoutListener();

        // Set up views with data
        // Game title
        titleGameToolbar.setText(infoGame.getName());
        //titleGame.setText(infoGame.getName());

        // Game image
        Picasso.get()
                .load(infoGame.getBackground_image())
                .resize(750, 500)
                //Todo add error loading
                .into(backImage);

        // Game rating
        rateGameToolbar.setText(Double.toString(infoGame.getRating()));
        //rateGame.setText(Double.toString(infoGame.getRating()));

        // Game release date
        releaseDate.setText(infoGame.getReleased());

        // Game Description
        //description.setText(infoGame.getDescription());
        description.setText(HtmlCompat.fromHtml(infoGame.getDescription(), HtmlCompat.FROM_HTML_MODE_COMPACT));


        // Game platforms
        StringBuilder platformsString = new StringBuilder();
        platformsString.append("Platforms: ");
        for (ParentPlatform platformItem : infoGame.getParent_platforms()) {
            platformsString.append(platformItem.getPlatform().getName()).append(", ");
        }

        if (platformsString.length() > 2) {
            platformsString.setLength(platformsString.length() - 2);
        }
        platforms.setText(platformsString);

        // Game genres
        StringBuilder genresString = new StringBuilder();
        genresString.append("Genres: ");
        for (Genre genreItem : infoGame.getGenres()) {
            genresString.append(genreItem.getName()).append(", ");
        }

        if (genresString.length() > 2) {
            genresString.setLength(genresString.length() - 2);
        }
        genres.setText(genresString);

        // Game publisher
        StringBuilder publisherString = new StringBuilder();
        publisherString.append("Publisher: ");
        for (Publisher publisherItem : infoGame.getPublishers()) {
            publisherString.append(publisherItem.getName()).append(", ");
        }

        if (publisherString.length() > 2) {
            publisherString.setLength(publisherString.length() - 2);
        }
        publisher.setText(publisherString);

        // Game video
        findViewById(R.id.video).setVisibility(View.GONE);
        clipGame.setVisibility(View.GONE);

        // Game screenshots
        findViewById(R.id.screen_shots).setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);

        // Bookmarks
        AddBookmark.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AddBookmark.setVisibility(View.GONE);
                RemoveBookmark.setVisibility(View.VISIBLE);
                addBookmark(String.valueOf(infoGame.getId()));

            }
        });
        RemoveBookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RemoveBookmark.setVisibility(View.GONE);
                AddBookmark.setVisibility(View.VISIBLE);
                removeBookmark(String.valueOf(infoGame.getId()));
            }
        });
        isBookmarked(String.valueOf(infoGame.getId()));


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

    private void isBookmarked(String gameId){
        DatabaseReference userBookmarksRef = databaseReference.child("users").child(user).child("bookmarks");
        userBookmarksRef.child(gameId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Bookmark exists
                    RemoveBookmark.setVisibility(View.VISIBLE);
                    AddBookmark.setVisibility(View.GONE);
                } else {
                    // Bookmark does not exist
                    AddBookmark.setVisibility(View.VISIBLE);
                    RemoveBookmark.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void addBookmark(String gameId){
        DatabaseReference userBookmarksRef = databaseReference.child("users").child(user).child("bookmarks");
        //userBookmarksRef.child(gameId).setValue(true);
        userBookmarksRef.child(gameId).child("game_id").setValue(gameId);
        userBookmarksRef.child(gameId).child("game_name").setValue(infoGame.getName());
        userBookmarksRef.child(gameId).child("slug").setValue(infoGame.getSlug());
        userBookmarksRef.child(gameId).child("background_image").setValue(infoGame.getBackground_image());
        userBookmarksRef.child(gameId).child("description").setValue(infoGame.getDescription_raw());
        userBookmarksRef.child(gameId).child("genres").setValue(genres.getText().toString().replace("Genres: ", ""));
        userBookmarksRef.child(gameId).child("platforms").setValue(platforms.getText().toString().replace("Platforms: ", ""));
        userBookmarksRef.child(gameId).child("publisher").setValue(publisher.getText().toString().replace("Publisher: ", ""));

    }
    private void removeBookmark(String gameId){
        DatabaseReference userBookmarksRef = databaseReference.child("users").child(user).child("bookmarks");
        userBookmarksRef.child(gameId).removeValue();
    }

}
