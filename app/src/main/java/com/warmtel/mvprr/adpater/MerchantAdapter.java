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
import com.warmtel.mvprr.bean.MerchantBean;

import java.util.ArrayList;

public class MerchantAdapter extends BaseAdapter {
    private ArrayList<MerchantBean> merchantList = new ArrayList<MerchantBean>();
    private LayoutInflater layoutInflater;
    private Context context;

    public MerchantAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setListData(ArrayList<MerchantBean> list) {
        this.merchantList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return merchantList.size();
    }

    @Override
    public Object getItem(int position) {
        return merchantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        final ViewHodler holder;
        if (convertView == null) {
            v = layoutInflater.inflate(R.layout.item_merchat_layout, null);

            holder = new ViewHodler();
            holder.iconImg = (ImageView) v
                    .findViewById(R.id.merchant_icon_img);
            holder.nameTxt = (TextView) v
                    .findViewById(R.id.merchant_name_txt);
            holder.couponTxt = (TextView) v
                    .findViewById(R.id.merchant_coupon_txt);
            holder.loactionTxt = (TextView) v
                    .findViewById(R.id.merchant_loaction_txt);
            holder.distanceTxt = (TextView) v
                    .findViewById(R.id.merchant_distance_txt);
            holder.cardImg = (ImageView) v
                    .findViewById(R.id.merchant_card_img);
            holder.groupImg = (ImageView) v
                    .findViewById(R.id.merchant_group_img);
            holder.conponImg = (ImageView) v
                    .findViewById(R.id.merchant_counp_img);

            v.setTag(holder);
        } else {
            v = convertView;
            holder = (ViewHodler) v.getTag();
        }

        MerchantBean merchant = (MerchantBean) getItem(position);

//            AsyncMemoryFileCacheImageLoader.getInstance(context).loadBitmap(
//                    getResources(), merchant.getPicUrl(), holder.iconImg);
        Glide.with(context).load(merchant.getPicUrl()).into(holder.iconImg);

        holder.nameTxt.setText(merchant.getName());
        holder.couponTxt.setText(merchant.getCoupon());
        holder.loactionTxt.setText(merchant.getLocation());
        holder.distanceTxt.setText(merchant.getDistance());

        if (merchant.getCardType().equalsIgnoreCase("YES")) {
            holder.cardImg.setVisibility(View.VISIBLE);
        } else {
            holder.cardImg.setVisibility(View.GONE);
        }

        if (merchant.getGroupType().equalsIgnoreCase("YES")) {
            holder.groupImg.setVisibility(View.VISIBLE);
        } else {
            holder.groupImg.setVisibility(View.GONE);
        }

        if (merchant.getCouponType().equalsIgnoreCase("YES")) {
            holder.conponImg.setVisibility(View.VISIBLE);
        } else {
            holder.conponImg.setVisibility(View.GONE);
        }
        return v;
    }

    public class ViewHodler {
        ImageView iconImg; // 图标
        TextView nameTxt; // 标题
        TextView couponTxt; // 打折信息
        TextView loactionTxt; // 地址
        TextView distanceTxt; // 距离
        ImageView cardImg; // 卡
        ImageView groupImg; // 团
        ImageView conponImg; // 券
    }
}
