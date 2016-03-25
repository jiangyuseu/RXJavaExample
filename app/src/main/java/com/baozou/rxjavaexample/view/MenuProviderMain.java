package com.baozou.rxjavaexample.view;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.baozou.rxjavaexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangyu on 2016/3/25.
 */
public class MenuProviderMain extends ActionProvider {

    private Context ctx;

    private View.OnClickListener listener;

    public MenuProviderMain(Context context) {
        super(context);
        ctx = context;
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.actionbar_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public void setOnClickLister(View.OnClickListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.location_img)
    public void onClick(View view) {
        this.listener.onClick(view);
    }
}
