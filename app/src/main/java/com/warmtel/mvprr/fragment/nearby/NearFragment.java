package com.warmtel.mvprr.fragment.nearby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.warmtel.expandtab.ExpandPopTabView;
import com.warmtel.expandtab.KeyValueBean;
import com.warmtel.expandtab.PopOneListView;
import com.warmtel.expandtab.PopTwoListView;
import com.warmtel.mvprr.BaseMvpFragment;
import com.warmtel.mvprr.R;
import com.warmtel.mvprr.adpater.MerchantAdapter;
import com.warmtel.mvprr.bean.CirclesBean;
import com.warmtel.mvprr.bean.ConfigInfo;
import com.warmtel.mvprr.bean.ConfigResult;
import com.warmtel.mvprr.presenter.NearbyPresenter;
import com.warmtel.mvprr.view.INearbyView;

import java.util.ArrayList;
import java.util.List;

public class NearFragment extends BaseMvpFragment<INearbyView, NearbyPresenter> implements INearbyView {
    private ExpandPopTabView mExpandPopTabView;
    private ListView mListView;

    public static NearFragment newInstance() {
        NearFragment fragment = new NearFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_near_layout, container, false);
    }

    @Override
    public NearbyPresenter initPresenter() {
        return new NearbyPresenter(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mExpandPopTabView = (ExpandPopTabView) getView().findViewById(R.id.near_expandpoptabview);
        mListView = (ListView) getView().findViewById(R.id.merchant_listview);
        mPresenter.setListAdapter();
        mPresenter.setExpandPopTabViewData();
        mPresenter.setListViewData();
    }

    @Override
    public void setmExpandPopTabData(ConfigResult data) {
        ConfigInfo info = data.getInfo();

        List<KeyValueBean> mParentLists = new ArrayList<>();//父区域
        List<ArrayList<KeyValueBean>> mChildrenListLists = new ArrayList<>();//子区域
        for (CirclesBean circlesBean : info.getAreaKey()) {
            KeyValueBean keyValueBean = new KeyValueBean();
            keyValueBean.setKey(circlesBean.getKey());
            keyValueBean.setValue(circlesBean.getValue());
            mParentLists.add(keyValueBean);
            mChildrenListLists.add((ArrayList<KeyValueBean>) circlesBean.getCircles());
        }
        mExpandPopTabView.removeAllViews();
        addItem(mExpandPopTabView, info.getDistanceKey(), "", "距离");
        addItem(mExpandPopTabView, info.getIndustryKey(), "", "行业");
        addItem(mExpandPopTabView, info.getSortKey(), "", "排序");
        addItem(mExpandPopTabView, mParentLists, mChildrenListLists, "锦江区", "合江亭", "区域");
    }

    @Override
    public void setListAdapter(MerchantAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(getActivity());
        popOneListView.setDefaultSelectByValue(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {
                //弹出框选项点击选中回调方法
                showToastMessage(value);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
                        List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
        PopTwoListView popTwoListView = new PopTwoListView(getActivity());
        popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
        popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                showToastMessage(showText);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
    }

}
