package com.example.recycleradapterhelper.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class FenZuInfo1 extends SectionEntity<String> {

    //isHeader true 就是组的构造 ，如果是false就是成员的构造
    public FenZuInfo1(boolean isHeader, String header) {
        super(isHeader, header);
    }

    //就是成员的构造
    public FenZuInfo1(String str) {
        super(str);
    }
}
