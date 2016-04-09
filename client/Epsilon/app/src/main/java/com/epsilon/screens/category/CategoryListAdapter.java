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

import java.util.ArrayList;
import java.util.List;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private static final String TAG = "CategoryListAdapter";

    private List<Category> mCategories;
    private String[] mTagColors;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public CategoryListAdapter(Context context) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        View v = holder.itemView;
        View colorTag = v.findViewById(R.id.category_view_color);
        colorTag.setBackgroundColor(Color.parseColor(mTagColors[position % mTagColors.length]));

        TextView categoryName = (TextView)v.findViewById(R.id.category_tv_categoryname);
        categoryName.setText(mCategories.get(position).getName());
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
}
