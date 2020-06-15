package com.wsy.everylesson.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.wsy.everylesson.R;

public class Fragment_Search extends DialogFragment {

    private RadioGroup btn_close;
    private View chuyi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.search, container, false);
        btn_close = inflate.findViewById(R.id.group1);
        chuyi = inflate.findViewById(R.id.chuyi);
        chuyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "初一", Toast.LENGTH_SHORT).show();
                // 关闭对话框 
//                dismiss();
            }
        });
return inflate;

    }
}
