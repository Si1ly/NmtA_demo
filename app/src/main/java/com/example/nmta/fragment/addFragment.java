package com.example.nmta.fragment;

import static android.app.Activity.RESULT_OK;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;


import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.nmta.R;
import com.example.nmta.api.API_Service;
import com.example.nmta.api.Service;
import com.example.nmta.data.VideoItem.VideoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class addFragment extends Fragment {

    PlayerView playerView;
    EditText editText;
    Button bt_add;
    View view;

    API_Service apiService;
    Service service;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        playerView.setOnClickListener(view1 -> {
            Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            getUri.launch(i);
        });
        
            bt_add.setOnClickListener(view12 -> {
                try {
                    if(playerView.getPlayer().getMediaMetadata() != null){
                        sendData();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Hãy chọn video của bạn", Toast.LENGTH_SHORT).show();
                }
            });
        
    }

    private void init(){
        playerView = view.findViewById(R.id.camera_View);
        bt_add = view.findViewById(R.id.bt_add);
        editText = view.findViewById(R.id.edt_addUser);

        API_Service apiService = new API_Service();
        service = apiService.getRetrofit().create(Service.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ActivityResultLauncher<Intent> getUri = registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    Uri uri = result.getData().getData();
                    addVideoUri(uri);
                }
            });

    private void addVideoUri(Uri uri) {
        ExoPlayer exoPlayer = new ExoPlayer.Builder(getContext()).build();
        MediaItem mediaItem = MediaItem.fromUri(uri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
        playerView.setPlayer(exoPlayer);
    }

    private void sendData(){
        String uri_temp = playerView.getPlayer().getMediaMetadata().toString();
        String user_temp = editText.getText().toString();
        VideoItem videoItem = new VideoItem(uri_temp,user_temp);

        service.saveVideo(videoItem).enqueue(new Callback<VideoItem>() {
            @Override
            public void onResponse(Call<VideoItem> call, Response<VideoItem> response) {

            }

            @Override
            public void onFailure(Call<VideoItem> call, Throwable t) {

            }
        });
    }
}