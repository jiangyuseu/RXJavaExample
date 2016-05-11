package com.baozou.rxjavaexample.view;

import android.app.Activity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.model.ItemBean;
import com.baozou.rxjavaexample.units.main.view.adapter.MainCategoryAdapter;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangyu on 2016/5/11.
 * 首页分类一级入口View
 */
public class MainCategoryView extends HeaderViewInterface<List<ItemBean>> {

    @Bind(R.id.main_item)
    public FixedGridView itemsView;

    public MainCategoryView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<ItemBean> itemBeans, ListView listView) {
        View view = mInflate.inflate(R.layout.adapteritem_main_item, listView, false);
        ButterKnife.bind(this, view);
        dealWithTheView(itemBeans);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<ItemBean> list) {
        int size = list.size();

        if (size <= 4) {
            itemsView.setNumColumns(size);
        } else if (size == 6) {
            itemsView.setNumColumns(3);
        } else if (size == 8) {
            itemsView.setNumColumns(4);
        } else {
            itemsView.setNumColumns(4);
        }

        MainCategoryAdapter adapter = new MainCategoryAdapter(mContext, list);
        itemsView.setAdapter(adapter);
    }

}
