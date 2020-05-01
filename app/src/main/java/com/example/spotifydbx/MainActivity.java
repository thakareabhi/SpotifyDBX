package com.example.spotifydbx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {
    private static final String CLIENT_ID = "e0e904fd848244cf8b852d6d80cec35b";
    private static final String REDIRECT_URI = "http://SpotifyDBX.com/callback/";
    private SpotifyAppRemote mSpotifyAppRemote;
    private Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);

        b1.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "SONG1", Toast.LENGTH_SHORT).show();
            mSpotifyAppRemote.getPlayerApi().play("spotify:track:5t8pYMK3M2ZT4uJOvABoMC");
        });
        b2.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "SONG2", Toast.LENGTH_SHORT).show();
            mSpotifyAppRemote.getPlayerApi().play("spotify:track:1smFN2CLqGROu0J0UyvDfL");
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // We will start writing our code here.
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    private void connected() {
        // Then we will write some more code here.

    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.
    }
}
