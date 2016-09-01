package com.warmtel.mvprr.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.warmtel.mvprr.R;
import com.warmtel.mvprr.bean.NewsTextItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻适配器
 */
public class ReadNewsAdapter extends BaseAdapter {
    List<NewsTextItemBean> readListAll = new ArrayList<>();
    LayoutInflater inflater;
    Context mContext;
    private static final int TYPE_TXT = 0;
    private static final int TYPE_IMG = 1;

    public ReadNewsAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    // 封装方法
    public void addDatas(int CurrentPage, List<NewsTextItemBean> list) {
        // 用mCurrentPage判断是set数据 还是添加数据
        if (CurrentPage == 0) {
            setData(list);
        } else {
            addData(list);
        }
    }

    public void setData(List<NewsTextItemBean> list) {
        readListAll = list;
        notifyDataSetChanged();
    }

    public void addData(List<NewsTextItemBean> list) {
        if (list != null) {
            readListAll.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return readListAll.size();
    }

    @Override
    public Object getItem(int position) {
        return readListAll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (readListAll.get(position).getImgextra().isEmpty()) {
            return TYPE_TXT;
        } else {
            return TYPE_IMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type == TYPE_TXT) {
            return getOneView(position, convertView, parent);
        } else {
            return getTwoView(position, convertView, parent);
        }
    }

    public View getOneView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.view_newstext_text_item, null);
            viewHolder.descriptionTxt = (TextView) convertView.findViewById(R.id.read_news_show_digest);
            viewHolder.titleTxt = (TextView) convertView.findViewById(R.id.read_news_show_title);
            viewHolder.newsImg = (ImageView) convertView.findViewById(R.id.read_news_show_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewsTextItemBean readObj = (NewsTextItemBean) getItem(position);

        viewHolder.descriptionTxt.setText(readObj.getDigest());
        viewHolder.titleTxt.setText(readObj.getTitle());

        Glide.with(mContext).load(readObj.getImgsrc()).into(viewHolder.newsImg);
        return convertView;
    }

    public View getTwoView(int position, View convertView, ViewGroup parent) {
        ViewHolder_IMG viewHolder_IMG = null;
        if (convertView == null) {
            viewHolder_IMG = new ViewHolder_IMG();
            convertView = inflater.inflate(R.layout.view_newstext_img_item, null);
            viewHolder_IMG.newsTitleTxt = (TextView) convertView.findViewById(R.id.read_news_img_title);
            viewHolder_IMG.newsOneImg = (ImageView) convertView.findViewById(R.id.read_news_img_pic1);
            viewHolder_IMG.newsTwoImg = (ImageView) convertView.findViewById(R.id.read_news_img_pic2);
            viewHolder_IMG.newsThreeImg = (ImageView) convertView.findViewById(R.id.read_news_img_pic3);
            convertView.setTag(viewHolder_IMG);
        } else {
            viewHolder_IMG = (ViewHolder_IMG) convertView.getTag();
        }
        NewsTextItemBean readObj = (NewsTextItemBean) getItem(position);
        viewHolder_IMG.newsTitleTxt.setText(readObj.getTitle());

        Glide.with(mContext).load(readObj.getImgsrc()).into( viewHolder_IMG.newsOneImg);

        if (readObj.getImgextra().size() >= 2) {
            Glide.with(mContext).load(readObj.getImgextra().get(0).getImgsrc()).into( viewHolder_IMG.newsTwoImg);
            Glide.with(mContext).load(readObj.getImgextra().get(1).getImgsrc()).into( viewHolder_IMG.newsThreeImg);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView newsImg;
        TextView titleTxt;
        TextView descriptionTxt;
    }

    class ViewHolder_IMG {
        ImageView newsOneImg;
        ImageView newsTwoImg;
        ImageView newsThreeImg;
        TextView newsTitleTxt;
    }

}
