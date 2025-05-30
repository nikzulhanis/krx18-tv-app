package com.krx18tv.network;

import com.krx18tv.models.VideoItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ContentFetcher {
    public static List<VideoItem> fetchVideos() throws Exception {
        List<VideoItem> videos = new ArrayList<>();
        Document doc = Jsoup.connect("https://krx18.com").get();
        Elements elements = doc.select(".video-item");

        for (Element el : elements) {
            VideoItem item = new VideoItem();
            item.title = el.select("h2").text();
            item.thumbnailUrl = el.select("img").attr("src");
            item.videoUrl = el.select("a").attr("href");
            videos.add(item);
        }

        return videos;
    }
}
