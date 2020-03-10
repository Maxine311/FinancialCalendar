package com.example.maxine.myapplication12;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.maxine.myapplication12.group.GroupRecyclerAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 适配器
 * Created by huanghaibin on 2017/12/4.
 */

public class ArticleAdapter extends GroupRecyclerAdapter<String, Article> {


    private RequestManager mLoader;

    public ArticleAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        LinkedHashMap<String, List<Article>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        map.put("今日推薦", create(0));
        map.put("每周熱點", create(1));
        map.put("最高評論", create(2));
        titles.add("今日推薦");
        titles.add("每周熱點");
        titles.add("最高評論");
        resetGroups(map,titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Article item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(item.getTitle());
        h.mTextContent.setText(item.getContent());
        RequestOptions options = new RequestOptions()
                .centerCrop();
//        mLoader.asBitmap().apply(options)
//                .load(item.getImgUrl())
//
//                .apply(options)
//                .centerCrop()
//                .into(h.mImageView);
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private ImageView mImageView;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
//            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }


    private static Article create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
//        article.setImgUrl(imgUrl);
        return article;
    }

    private static List<Article> create(int p) {
        List<Article> list = new ArrayList<>();
        if (p == 0) {
            list.add(create("Google",
                    "讓我來試試"));
            list.add(create("啦啦啦",
                    "GG我的圖長不出來"));
            list.add(create("GoGoGo",
                    "Catch me if you can"));
            list.add(create("我的CODE",
                    "我的CODE就像便秘三個月，大不出來但是積一堆"));
            list.add(create("顆顆顆",
                    "笑吧笑吧"));
        } else if (p == 1) {
            list.add(create(
                    "讓我來想想標題",
                    "恩還是想不到"));
            list.add(create(
                    "什麼要跨年了！！",
                    "讓我們一起抱著ＣＯＤＥ倒數"));
            list.add(create(
                    "來點播一首歌",
                    "我也不知道你想聽什麼"));
            list.add(create(
                    "一起嗨！！！",
                    "我們一起上火星"));
        } else if (p == 2) {
            list.add(create(
                    "你好嗎？",
                    "我很好，只是CODE生不出來"));
            list.add(create(
                    "12點了",
                    "沒有灰姑娘要回家，只有肚子餓的我"));
            list.add(create("想睡覺",
                    "醒醒啊，想想你的CODE在呼喚你"));
            list.add(create("可以空白嗎？",
                    " "));
            list.add(create(
                    "繼續偷懶",
                    ""));
        }


        return list;
    }
}
