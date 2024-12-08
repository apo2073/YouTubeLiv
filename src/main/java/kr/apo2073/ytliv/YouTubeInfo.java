package kr.apo2073.ytliv;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YouTubeInfo {
    private final String channelName;
    private final String channelSubscriptionCount;
    private final Channel channel;
    private static String API_KEY;

    public static YouTubeInfo from(String videoId, String api) {
        try {
            YouTube youtube = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null
            ).setApplicationName("YouTubeLiv").build();
            API_KEY=api;

            return new YouTubeInfo(youtube, videoId);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private YouTubeInfo(YouTube youtube, String videoId) {
        Channel tempChannel = null;
        String tempName = null;
        String tempCount = null;

        try {
            tempChannel = getChannel(youtube, videoId);
            if (tempChannel != null) {
                tempName = tempChannel.getSnippet().getTitle();
                tempCount = tempChannel.getStatistics().getSubscriberCount().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.channel = tempChannel;
        this.channelName = tempName;
        this.channelSubscriptionCount = tempCount;
    }

    private Channel getChannel(YouTube youtube, String videoId) throws IOException {
        YouTube.Videos.List videoRequest = youtube.videos()
                .list(List.of("snippet"))
                .setKey(API_KEY)
                .setId(List.of(videoId));

        var videoResponse = videoRequest.execute();
        if (videoResponse.getItems() == null || videoResponse.getItems().isEmpty()) return null;
        String channelId = videoResponse.getItems().get(0).getSnippet().getChannelId();

        YouTube.Channels.List channelRequest = youtube.channels()
                .list(List.of("snippet", "statistics"))
                .setKey(API_KEY)
                .setId(List.of(channelId));

        var channelResponse = channelRequest.execute();
        if (channelResponse.getItems() == null || channelResponse.getItems().isEmpty()) return null;

        return channelResponse.getItems().get(0);
    }

    public String getChannelName() {
        return channelName;
    }

    public String getSubscriptionCount() {
        return channelSubscriptionCount;
    }

    public Channel getChannel() {
        return channel;
    }
}
