package com.autocompletedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 11-04-2016.
 */
public class RestaurantList implements Parcelable
{
    public static final String TAG_DEALER_ID = "rest_id";
    public static final String TAG_USER_ID = "rest_name";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_CITY = "city";
    public static final String TAG_STATE = "state";
    public static final String TAG_COUNTRY = "country";
    public static final String TAG_EMAIL = "email_id";
    public static final String TAG_IS_PICKUP = "is_pickup";
    public static final String TAG_IS_DELIVERY = "is_delivery";
    public static final String TAG_SYS_DELIVERY = "sys_delivery";
    public static final String TAG_IS_PREORDER = "is_preorder";
    public static final String TAG_REST_IMAGE = "rest_image";
    public static final String TAG_REST_AREA = "rest_area";
    public static final String TAG_DELIVERY_CHARGE = "delivery_cherg";
    public static final String TAG_PER_MILE_CHARGE = "per_mile_delivery_chr";
    public static final String TAG_FIXED_MILE = "fixed_mile";
    public static final String TAG_MINIMUM_MILE = "minimum_mile";
    public static final String TAG_DEL_MIN_ORDER = "del_min_order";
    public static final String TAG_REVIEW_COUNT = "review_count";
    public static final String TAG_RATING_IMAGE = "rating_img_url";
    public static final String TAG_RATING = "rating";
    public static final String TAG_CATEGORIES = "categories";

    String rest_id, rest_name, address, city, state, country, email, is_pickup, is_delivery,
            sys_delivery, is_preorder, rest_image, rest_area, delivery_cherg, per_mile_delivery_chr, fixed_mile, minimum_mile,
            del_min_order, review_count, rating_img_url, rating, categorystr;


    public String getRestID() {
        return rest_id;
    }

    public void setRestID(String name) {
        this.rest_id = name;
    }

    public String getRestName() {
        return rest_name;
    }

    public void setRestName(String name) {
        this.rest_name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String name) {
        this.address = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String name) {
        this.city = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String name) {
        this.state = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String name) {
        this.country = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getIsPickup() {
        return is_pickup;
    }

    public void setIsPickup(String name) {
        this.is_pickup = name;
    }

    public String getIsDelivery() {
        return is_delivery;
    }

    public void setIsDelivery(String name) {
        this.is_delivery = name;
    }

    public String getSysDelivery() {
        return sys_delivery;
    }

    public void setSysDelivery(String name) {
        this.sys_delivery = name;
    }

    public String getIsPreorder() {
        return is_preorder;
    }

    public void setIsPreorder(String name) {
        this.is_preorder = name;
    }

    public String getRestImage() {
        return rest_image;
    }

    public void setRestImage(String name) {
        this.rest_image = name;
    }

    public String getRestArea() {
        return rest_area;
    }

    public void setRestArea(String name) {
        this.rest_area = name;
    }

    public String getDeliveryCharge() {
        return delivery_cherg;
    }

    public void setDeliveryCharge(String name) {
        this.delivery_cherg = name;
    }

    public String getPerMileCharge() {
        return per_mile_delivery_chr;
    }

    public void setPerMileCharge(String name) {
        this.per_mile_delivery_chr = name;
    }

    public String getFixedMile() {
        return fixed_mile;
    }

    public void setFixedMile(String name) {
        this.fixed_mile = name;
    }

    public String getMinimumMile() {
        return minimum_mile;
    }

    public void setMinimumMile(String name) {
        this.minimum_mile = name;
    }

    public String getDelMinimumOrder() {
        return del_min_order;
    }

    public void setDelMinimumOrder(String name) {
        this.del_min_order = name;
    }

    public String getReviewCount() {
        return review_count;
    }

    public void setReviewCount(String name) {
        this.review_count = name;
    }

    public String getRatingImage() {
        return rating_img_url;
    }

    public void setRatingImage(String name) {
        this.rating_img_url = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String name) {
        this.rating = name;
    }

    public String getCategorystr() {
        return categorystr;
    }

    public void setCategorystr(String name) {
        this.categorystr = name;
    }

    public RestaurantList() {
        // TODO Auto-generated constructor stub
    }

    public RestaurantList(Parcel in)
    {
        rest_id = in.readString();
        rest_name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        email = in.readString();
        is_pickup = in.readString();
        is_delivery = in.readString();
        sys_delivery = in.readString();
        is_preorder = in.readString();
        rest_image = in.readString();
        rest_area = in.readString();
        delivery_cherg = in.readString();
        per_mile_delivery_chr = in.readString();
        fixed_mile = in.readString();
        minimum_mile = in.readString();
        del_min_order = in.readString();
        review_count = in.readString();
        rating_img_url = in.readString();
        rating = in.readString();
        categorystr = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(rest_id);
        dest.writeString(rest_name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(email);
        dest.writeString(is_pickup);
        dest.writeString(is_delivery);
        dest.writeString(sys_delivery);
        dest.writeString(is_preorder);
        dest.writeString(rest_image);
        dest.writeString(rest_area);
        dest.writeString(delivery_cherg);
        dest.writeString(per_mile_delivery_chr);
        dest.writeString(fixed_mile);
        dest.writeString(minimum_mile);
        dest.writeString(review_count);
        dest.writeString(del_min_order);
        dest.writeString(rating_img_url);
        dest.writeString(rating);
        dest.writeString(categorystr);
    }

    public static final Creator<RestaurantList> CREATOR = new Creator<RestaurantList>() {
        @Override
        public RestaurantList createFromParcel(Parcel in) {
            // TODO Auto-generated method stub
            return new RestaurantList(in);
        }

        @Override
        public RestaurantList[] newArray(int size) {
            // TODO Auto-generated method stub
            return new RestaurantList[size];
        }
    };
}
