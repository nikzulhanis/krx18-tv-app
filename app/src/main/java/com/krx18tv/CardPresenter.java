package com.krx18tv;

import android.view.ViewGroup;
import androidx.leanback.widget.*;
import com.krx18tv.models.VideoItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import android.view.View;

public class CardPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageCardView cardView = new ImageCardView(parent.getContext());
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        VideoItem video = (VideoItem) item;
        ImageCardView cardView = (ImageCardView) viewHolder.view;

        cardView.setTitleText(video.title);
        cardView.setContentText("");
        cardView.setMainImageDimensions(313, 176);

        Glide.with(cardView.getContext())
            .load(video.thumbnailUrl)
            .apply(new RequestOptions().centerCrop())
            .into(cardView.getMainImageView());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Clean up resources if needed
    }
}
