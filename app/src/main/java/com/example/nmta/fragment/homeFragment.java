package com.example.nmta.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nmta.R;
import com.example.nmta.adapter.RecycleView_Adapter;
import com.example.nmta.api.API_Service;
import com.example.nmta.api.Service;
import com.example.nmta.data.VideoItem.VideoItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {

    List<VideoItem> videoItemList;
    RecyclerView recyclerView;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textView = view.findViewById((R.id.textView_test));

        recyclerView = view.findViewById(R.id.recycleView_Home_Fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        initData();
    }


    public void initData(){

        API_Service apiService = new API_Service();
        Service service = apiService.getRetrofit().create(Service.class);
        
        service.getAllVideo().enqueue(new Callback<List<VideoItem>>() {
            @Override
            public void onResponse(Call<List<VideoItem>> call, Response<List<VideoItem>> response) {
               recyclerView.setAdapter(new RecycleView_Adapter(response.body(),getContext()));
            }

            @Override
            public void onFailure(Call<List<VideoItem>> call, Throwable t) {

            }
        });

    }


}