package com.krx18tv;

import android.content.Intent;
import android.os.Bundle;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.*;

import com.krx18tv.models.VideoItem;
import com.krx18tv.network.ContentFetcher;

import java.util.List;

public class VideoGridFragment extends BrowseSupportFragment {
    private ArrayObjectAdapter rowsAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("krx18 TV");
        loadRows();
    }

    private void loadRows() {
        new Thread(() -> {
            try {
                List<VideoItem> videos = ContentFetcher.fetchVideos();
                rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
                ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());

                for (VideoItem video : videos) {
                    listRowAdapter.add(video);
                }

                HeaderItem header = new HeaderItem(0, "Latest Videos");
                rowsAdapter.add(new ListRow(header, listRowAdapter));

                getActivity().runOnUiThread(() -> setAdapter(rowsAdapter));

                setOnItemViewClickedListener((itemViewHolder, item, rowViewHolder, row) -> {
                    VideoItem video = (VideoItem) item;
                    Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                    intent.putExtra("VIDEO_URL", video.videoUrl);
                    startActivity(intent);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
