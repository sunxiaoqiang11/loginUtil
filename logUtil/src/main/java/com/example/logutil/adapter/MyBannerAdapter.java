package com.example.logutil.adapter;

import com.example.smartcity_1.utils.Ok;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;
import java.util.Map;

public class MyBannerAdapter extends BannerImageAdapter<Object> {
    public MyBannerAdapter(List<Object> mData) {
        super(mData);
    }

    @Override
    public void onBindView(BannerImageHolder bannerImageHolder, Object o, int i, int i1) {
        if (o instanceof Map) {
            Ok.INSTANCE.setImage(((Map<String,Object>) o).get("imgUrl").toString(),bannerImageHolder.imageView);
        }else bannerImageHolder.imageView.setImageResource((Integer) o);
    }
}
