package com.epsilon.screens.category;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epsilon.R;
import com.epsilon.models.entities.Category;
import com.epsilon.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private static final String TAG = "CategoryListAdapter";
    private OnCategoryItemClick mOnCategoryItemClick;

    private List<Category> mCategories;
    private String[] mTagColors;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public CategoryListAdapter(Context context, OnCategoryItemClick onCategoryItemClick) {
        this.mOnCategoryItemClick = onCategoryItemClick;
        mTagColors = context.getResources().getStringArray(R.array.category_colors);
        mCategories = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        View v = holder.itemView;
        View colorTag = v.findViewById(R.id.category_view_color);
        colorTag.setBackgroundColor(Color.parseColor(mTagColors[position % mTagColors.length]));

        TextView categoryName = (TextView)v.findViewById(R.id.category_tv_categoryname);
        categoryName.setText(mCategories.get(position).getName());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.log(TAG, "Calling this");
                mOnCategoryItemClick.onClick(position, mCategories.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> mCategories) {
        this.mCategories = mCategories;
        notifyDataSetChanged();
    }

    public interface OnCategoryItemClick {
        void onClick(int position, Category category);
    }
}
