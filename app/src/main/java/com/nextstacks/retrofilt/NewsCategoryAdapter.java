package com.nextstacks.retrofilt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryHolder> {


    public Context context;
    public ArrayList<String> newsCategories;
    public int selectedPosition = -1;

    private NewsCategoryListener listener;

    public void setListener(NewsCategoryListener listener) {
        this.listener = listener;
    }

    public NewsCategoryAdapter(Context context, ArrayList<String> newsCategories) {
        this.context = context;
        this.newsCategories = newsCategories;
    }

    @NonNull
    @Override
    public NewsCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_news_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryHolder holder, int position) {
        String category = newsCategories.get(position);
        holder.mTvNewsCategory.setText(category);

        if (selectedPosition == position) {
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.selected, null));
            holder.mLlNewsCategory.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_selected, null));
        } else {
            holder.mTvNewsCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.unselected, null));
            holder.mLlNewsCategory.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_unselected, null));
        }

        holder.mLlNewsCategory.setOnClickListener(view -> {
            selectedPosition = position;
            notifyDataSetChanged();
            if (listener != null) {
                listener.onNewsCategorySelected(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsCategories.size();
    }

    class NewsCategoryHolder extends RecyclerView.ViewHolder {

        private TextView mTvNewsCategory;
        private LinearLayout mLlNewsCategory;

        public NewsCategoryHolder(@NonNull View itemView) {
            super(itemView);

            mTvNewsCategory = itemView.findViewById(R.id.tv_news_category);
            mLlNewsCategory = itemView.findViewById(R.id.ll_news_category);
        }
    }

    public interface NewsCategoryListener {
        void onNewsCategorySelected(String category);
    }
}
