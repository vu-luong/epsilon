package com.epsilon.screens.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.R;
import com.epsilon.models.entities.Category;

import java.util.List;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private static final String TAG = "CategoryListAdapter";

    private List<Category> mCategories;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



    public CategoryListAdapter() {
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


    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> mCategories) {
        this.mCategories = mCategories;
        notifyDataSetChanged();
    }
}
