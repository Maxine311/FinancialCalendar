package com.example.maxine.myapplication12.pager;

import android.support.v7.widget.LinearLayoutManager;

import com.example.maxine.myapplication12.Article;
import com.example.maxine.myapplication12.ArticleAdapter;
import com.example.maxine.myapplication12.R;
import com.example.maxine.myapplication12.base.fragment.BaseFragment;
import com.example.maxine.myapplication12.group.GroupItemDecoration;
import com.example.maxine.myapplication12.group.GroupRecyclerView;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    public static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = (GroupRecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(1000);
    }

    @Override
    protected void initData() {

    }

    public boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
