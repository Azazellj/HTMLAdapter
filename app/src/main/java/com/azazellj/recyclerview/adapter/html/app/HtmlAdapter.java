package com.azazellj.recyclerview.adapter.html.app;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azazellj.recyclerview.adapter.html.ImageViewHolder;
import com.azazellj.recyclerview.adapter.html.TextViewHolder;
import com.azazellj.recyclerview.adapter.html.VideoViewHolder;
import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;
import com.azazellj.recyclerview.adapter.html.base.HtmlType;
import com.azazellj.recyclerview.adapter.html.util.HtmlTool;
import com.bumptech.glide.Glide;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azazellj on 3/1/19.
 */
public class HtmlAdapter extends RecyclerView.Adapter {
    private List<Element> data = new ArrayList<>();

    public void setData(List<Element> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        Element item = getItem(position);
        if (item == null) {
            return -1;
        }

        if (HtmlTool.isElement(item, HtmlScheme.UL)) {
            return HtmlType.TEXT;
        }
        if (HtmlTool.isElement(item, HtmlScheme.OL)) {
            return HtmlType.TEXT;
        }
        if (HtmlTool.isElement(item, HtmlScheme.IMG)) {
            return HtmlType.IMAGE;
        }
        if (HtmlTool.isElement(item, HtmlScheme.YOUTUBE)) {
            return HtmlType.VIDEO;
        }

        return HtmlType.TEXT;
    }

    @Nullable
    private Element getItem(final int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }

        return data.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (viewType == HtmlType.TEXT) {
            return createTextViewHolder(inflater, viewGroup);
        }
        if (viewType == HtmlType.IMAGE) {
            return createImageViewHolder(inflater, viewGroup);
        }
        if (viewType == HtmlType.VIDEO) {
            return createVideoViewHolder(inflater, viewGroup);
        }

        return null;
    }


    private RecyclerView.ViewHolder createTextViewHolder(final LayoutInflater inflater, final ViewGroup parent) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.item_html_text, parent, false)) {
        };
    }

    private RecyclerView.ViewHolder createImageViewHolder(final LayoutInflater inflater, final ViewGroup parent) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.item_html_image, parent, false)) {
        };
    }

    private RecyclerView.ViewHolder createVideoViewHolder(final LayoutInflater inflater, final ViewGroup parent) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.item_html_video, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Element element = getItem(viewHolder.getAdapterPosition());
        if (element == null) return;

        int viewType = viewHolder.getItemViewType();

        if (viewType == HtmlType.TEXT) {
            TextViewHolder.initTextView(element, (TextView) viewHolder.itemView, null);
        }
        if (viewType == HtmlType.IMAGE) {
            String imageUrl = ImageViewHolder.initImageViewHolder(element, null);
            Glide.with(viewHolder.itemView).load(imageUrl).into((ImageView) viewHolder.itemView);
        }
        if (viewType == HtmlType.VIDEO) {
            VideoViewHolder.initYoutubeViewHolder(element, (ImageView) viewHolder.itemView, null);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
