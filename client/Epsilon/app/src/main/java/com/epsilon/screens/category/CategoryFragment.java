package com.epsilon.screens.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.customview.GridSpacingItemDecoration;
import com.epsilon.models.entities.Category;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Dandoh on 4/9/16.
 */
public class CategoryFragment extends GenericRetainedFragment implements CategoryContract.View{

    @Bind(R.id.discover_recycle_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.header)
    RecyclerViewHeader mHeader;

    private CategoryListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int numOfColumn = getResources().getInteger(R.integer.numOfColumn);

        mLayoutManager = new GridLayoutManager(getActivity(), numOfColumn);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mHeader.attachTo(mRecyclerView);

        mAdapter = new CategoryListAdapter();
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
        
    }
}
