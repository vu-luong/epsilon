package com.epsilon.screens.categorydetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.epsilon.EpsilonApplication;
import com.epsilon.R;
import com.epsilon.models.entities.Category;
import com.epsilon.models.entities.Course;
import com.epsilon.utils.Utils;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private static final String TAG = "CourseListAdapter";
    private OnCourseItemClick mOnCourseItemClick;
    private List<Course> mCourses;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CourseListAdapter(OnCourseItemClick onCourseItemClick) {
        mOnCourseItemClick = onCourseItemClick;
        mCourses = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        Log.i(TAG, "calling this");
//         create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        View v = holder.itemView;

        ImageView thumbNailImageView = (ImageView) v.findViewById(R.id.item_imgv_course_thumbnail);
        TextView courseNameTextView = (TextView) v.findViewById(R.id.item_tv_course_name);
        TextView teacherName = (TextView) v.findViewById(R.id.item_tv_teacher_name);
        TextView priceTextView = (TextView) v.findViewById(R.id.item_tv_price);
        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.item_tv_ratingbar);
        ImageView providerLogoImageView = (ImageView) v.findViewById(R.id.item_imgv_provider);


        final Course course = mCourses.get(position);

        // Set information
        Picasso.with(EpsilonApplication.getAppContext())
                .load(course.getImage_url())
                .into(thumbNailImageView);
        courseNameTextView.setText(course.getTitle());
        teacherName.setText(course.getAuthor_name());
        ratingBar.setRating(course.getRating());

        String price = course.getPrice();
        getRealPrice(price, priceTextView);

        String provider = course.getProvider();
        if (provider.equals("academy.vn")) {
            providerLogoImageView.setImageResource(R.drawable.icon_academy);
        } else if (provider.equals("edumall.vn")) {
            providerLogoImageView.setImageResource(R.drawable.icon_edumall);
        } else if (provider.equals("kyna.vn")) {
            providerLogoImageView.setImageResource(R.drawable.icon_kyna);
        } else if (provider.equals("3hoc.vn")) {
            providerLogoImageView.setImageResource(R.drawable.icon_bahoc);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCourseItemClick.onClick(position, course);
            }
        });

    }

    private void getRealPrice(String price, TextView priceTextView) {
        if (TextUtils.isEmpty(price)) {
            priceTextView.setText(" ");
        } else if (price.charAt(0) == 'P' ||
                price.charAt(0) == 'p') {
            priceTextView.setText(R.string.premium);
        } else if (price.charAt(0) == 'M' ||
                price.charAt(0) == 'm') {
            priceTextView.setText(R.string.free);
        } else {
            for (int i = 0; i < price.length(); i++) {
                if (price.charAt(i) == 'đ') {
                    priceTextView.setText(price.substring(0, i + 1));
                    break;
                }
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(List<Course> mCourses) {
        Utils.log(TAG, mCourses.size() + " ");

        this.mCourses = mCourses;
        notifyDataSetChanged();
    }

    public interface OnCourseItemClick {
        void onClick(int position, Course course);
    }
}