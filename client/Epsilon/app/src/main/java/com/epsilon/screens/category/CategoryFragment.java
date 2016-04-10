package com.epsilon.screens.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.customview.GridSpacingItemDecoration;
import com.epsilon.models.entities.Category;
import com.epsilon.screens.categorydetail.CategoryDetailActivity;
import com.epsilon.utils.Utils;

import java.util.List;

import butterknife.Bind;
import utils.Injection;

/**
 * Created by Dandoh on 4/9/16.
 */
public class CategoryFragment extends GenericRetainedFragment implements CategoryContract.View, CategoryListAdapter.OnCategoryItemClick {

    @Bind(R.id.discover_recycle_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.header)
    RecyclerViewHeader mHeader;

    private CategoryListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CategoryContract.UserActionListener mUserActionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionListener = new CategoryPresenter(this, Injection.provideCategoryRepository());
        mUserActionListener.getAllCategory();

        mAdapter = new CategoryListAdapter(getActivity().getApplicationContext(),
                this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        setUpCategoryList();


    }

    private void setUpCategoryList() {
        int numOfColumn = getResources().getInteger(R.integer.numOfColumn);

        mLayoutManager = new GridLayoutManager(getActivity(), numOfColumn);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mHeader.attachTo(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(numOfColumn, spacingInPixels, true));
    }

    @Override
    public void displayAllCategory(List<Category> categories) {
        mAdapter.setCategories(categories);
    }

    @Override
    public void goToCategoryCoursesScreen(int categoryId) {
        Intent intent = CategoryDetailActivity.makeIntent(getActivity(), categoryId);
        startActivity(intent);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int position, Category category) {
        mUserActionListener.viewCoursesOfCategory(category.getId());
    }
}
