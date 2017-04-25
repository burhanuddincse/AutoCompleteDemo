package com.autocompletedemo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autocompletedemo.R;
import com.autocompletedemo.model.RestaurantList;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by Android on 11-04-2016.
 */
public class RestaurantListAdapter extends ArrayAdapter<RestaurantList>
{
    private Context context;
    private int itemLayoutResource;
    Typeface myTypeface;

    public RestaurantListAdapter(Context context, int itemLayoutResource,
                                 ArrayList<RestaurantList> dealers)
    {
        super(context, itemLayoutResource, dealers);
        this.itemLayoutResource = itemLayoutResource;
        this.context = context;
    }

    public RestaurantListAdapter(Context context,
                                 ArrayList<RestaurantList> dealers) {
        super(context, 0, dealers);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.itemLayoutResource, null);
        }
        RestaurantList dealer = getItem(position);

        TextView name, tvMinimumOrder, tvCategorystr;
        TextView address, is_deliver, review_count;
        ImageView thumb, ratingimg;
        LinearLayout linearRating;

        tvMinimumOrder = (TextView)view.findViewById(R.id.tvMinimumOrder);
        linearRating = (LinearLayout)view.findViewById(R.id.linearRating);
        name = (TextView) view.findViewById(R.id.tvName);
        address = (TextView) view.findViewById(R.id.tvAddress);
        is_deliver = (TextView) view.findViewById(R.id.tvDelivery);
        review_count = (TextView) view.findViewById(R.id.tvReviewsCount);
        tvCategorystr = (TextView)view.findViewById(R.id.tvCategorystr);
        thumb = (ImageView) view.findViewById(R.id.ivThumb);

        Ion.with(context)
                .load(dealer.getRestImage())
                .withBitmap()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .intoImageView(thumb);

        ratingimg = (ImageView) view.findViewById(R.id.ivStars);

        if(dealer.getRatingImage().equals(""))
        {
            linearRating.setVisibility(View.GONE);
            tvCategorystr.setVisibility(View.GONE);
        }
        else
        {
            Ion.with(context)
                    .load(dealer.getRatingImage())
                    .withBitmap()
                    .placeholder(R.drawable.no_stars)
                    .error(R.drawable.no_stars)
                    .intoImageView(ratingimg);

            linearRating.setVisibility(View.VISIBLE);
            review_count.setText(dealer.getReviewCount() + " Reviews");

            if(!dealer.getCategorystr().equals(""))
            {
                tvCategorystr.setVisibility(View.VISIBLE);
                tvCategorystr.setText("Category(s) : " + dealer.getCategorystr().trim());
            }
        }

        if(dealer.getDelMinimumOrder().equals("0"))
        {
            tvMinimumOrder.setVisibility(View.GONE);
        }
        else
        {
            tvMinimumOrder.setVisibility(View.VISIBLE);
            tvMinimumOrder.setText("Minimum Order : $" + dealer.getDelMinimumOrder());
        }

        name.setText(dealer.getRestName());
        address.setText(dealer.getAddress());
        String chkdeliver = dealer.getIsDelivery();
        String chksysdeliver = dealer.getSysDelivery();
        if(chkdeliver.equals("1") || chksysdeliver.equals("1"))
        {
            is_deliver.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
