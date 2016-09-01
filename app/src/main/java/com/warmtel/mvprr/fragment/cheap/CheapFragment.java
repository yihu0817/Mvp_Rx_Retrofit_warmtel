package com.warmtel.mvprr.fragment.cheap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.warmtel.mvprr.BaseMvpFragment;
import com.warmtel.mvprr.R;
import com.warmtel.mvprr.adpater.ReadNewsAdapter;
import com.warmtel.mvprr.bean.AdsBean;
import com.warmtel.mvprr.common.BannerLayout;
import com.warmtel.mvprr.presenter.CheapPresenter;
import com.warmtel.mvprr.view.ICheapView;

import java.util.ArrayList;
import java.util.List;

import static com.warmtel.mvprr.R.layout.fragment_cheap_banner_layout;

public class CheapFragment extends BaseMvpFragment<ICheapView, CheapPresenter> implements ICheapView, View.OnClickListener {
    private EditText mSearchKey;
    private ListView mListView;
    private FrameLayout mListHeadView;
    private BannerLayout mBannerLayout;

    public static CheapFragment newInstance() {
        CheapFragment fragment = new CheapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListHeadView = (FrameLayout) inflater.inflate(fragment_cheap_banner_layout, null);
        return inflater.inflate(R.layout.fragment_cheap_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchKey = (EditText) getView().findViewById(R.id.com_search_edit);
        mBannerLayout = (BannerLayout) mListHeadView.findViewById(R.id.banner);
        mListView = (ListView) getView().findViewById(R.id.cheap_listview);
        mListView.addHeaderView(mListHeadView);
        mSearchKey.setOnClickListener(this);

        mPresenter.setListViewAdapter();
        mPresenter.setListBannerViewData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.com_search_edit:
                getActivity().onSearchRequested();
                break;
        }
    }

    @Override
    public CheapPresenter initPresenter() {
        return new CheapPresenter(getActivity());
    }

    @Override
    public void setListViewAdapter(ReadNewsAdapter adpater) {
        mListView.setAdapter(adpater);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBannerLayoutData(List<AdsBean> urls) {
        List<String> list = new ArrayList<>();
        for (AdsBean ads : urls) {
            list.add(ads.getImgsrc());
        }
        mBannerLayout.setViewUrls(list);
        //添加监听事件
        mBannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToastMessage(String.valueOf(position));
            }
        });
    }
}
