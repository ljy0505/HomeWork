package com.wsy.everylesson.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.wsy.everylesson.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointListFragment extends Fragment {


    private TextView mAppointTv;

    public AppointListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_appoint_list, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mAppointTv = (TextView) itemView.findViewById(R.id.appointListFragment);
    }

}
