package com.example.recycleradapterhelper.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class FenZuInfo2 extends SectionEntity<ChengYuanBean> {

    //isHeader true 就是组的构造 ，如果是false就是成员的构造
    public FenZuInfo2(boolean isHeader, String header) {
        super(isHeader, header);
    }

    //就是成员的构造
    public FenZuInfo2(ChengYuanBean chengYuanBean) {
        super(chengYuanBean);
    }
}
