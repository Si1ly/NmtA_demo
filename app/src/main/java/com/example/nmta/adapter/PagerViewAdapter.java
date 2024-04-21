package com.example.nmta.adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nmta.fragment.addFragment;
import com.example.nmta.fragment.homeFragment;

public class PagerViewAdapter extends FragmentStateAdapter {

    public PagerViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new homeFragment();
            case 1 : return new addFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
