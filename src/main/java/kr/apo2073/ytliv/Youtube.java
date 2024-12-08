
package kr.apo2073.ytliv;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.LiveChatMessage;
import kr.apo2073.ytliv.data.Chatting;
import kr.apo2073.ytliv.data.SuperChat;
import kr.apo2073.ytliv.data.SuperSticker;
import kr.apo2073.ytliv.enums.MessageType;
import kr.apo2073.ytliv.exception.InvalidApiKeyException;
import kr.apo2073.ytliv.exception.NullLiveChatId;
import kr.apo2073.ytliv.listener.YouTubeEventListener;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Youtube {
    private final String api;
    private final String videoID;
    private final List<YouTubeEventListener> listeners;
    private final long pollingInterval;
    private final boolean isDebug;
    private final YouTube youtube;
    private boolean isRunning = false;
    private Thread chatThread;

    public Youtube(YouTubeBuilder builder) throws GeneralSecurityException, IOException {
        this.api = builder.API_KEY;
        this.videoID = builder.VIDEO_ID;
        this.listeners = builder.listeners;
        this.isDebug = builder.isDebug;
        this.pollingInterval = builder.pollingInterval;
        this.youtube = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                null
        ).setApplicationName("YouTubeLiv") .build();

        validateApiKey();

        if (isRunning) return;
        isRunning = true;
        chatThread = new Thread(this::runChat);
        chatThread.start();
    }

    private void validateApiKey() throws IOException {
        try {
            YouTube.Videos.List request = youtube.videos()
                    .list(List.of("snippet"))
                    .setKey(api)
                    .setId(List.of("dQw4w9WgXcQ"));
            request.execute();
        } catch (GoogleJsonResponseException e) {
            if (e.getStatusCode() == 400 || e.getStatusCode() == 403) {
                throw new InvalidApiKeyException();
            }
            throw e;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getVideoId() {
        return videoID;
    }

    public void stop() {
        isRunning = false;
        if (chatThread == null) return;
        chatThread.interrupt();
    }

    private void runChat() {
        try {
            String liveChatId = getListChatID();
            if (liveChatId == null) {
                throw new NullLiveChatId();
            }

            String pageToken = null;
            while (isRunning) {
                try {
                    YouTube.LiveChatMessages.List liveChatRequest = youtube.liveChatMessages()
                            .list(liveChatId, List.of("snippet", "authorDetails"))
                            .setKey(api).setPageToken(pageToken);
                    var liveChatResponse = liveChatRequest.execute();

                    for (LiveChatMessage message : liveChatResponse.getItems()) {
                        processMessage(message);
                    }
                    pageToken = liveChatResponse.getNextPageToken();
                    Thread.sleep(liveChatResponse.getPollingIntervalMillis());
                } catch (Exception e) {
                    processError(e);
                    Thread.sleep(pollingInterval);
                }
            }
        } catch (Exception e) {
            processError(e);
        }
    }

    private String getListChatID() throws IOException {
        YouTube.Videos.List request = youtube.videos()
                .list(List.of("liveStreamingDetails"))
                .setKey(api)
                .setId(List.of(videoID));
        var response = request.execute();
        if (response.getItems() == null || response.getItems().get(0).getLiveStreamingDetails() == null) return null;
        return response.getItems().get(0).getLiveStreamingDetails().getActiveLiveChatId();
    }

    private void processMessage(LiveChatMessage message) {
        if (!isRunning) return;

        if (message.getSnippet().getType().equals(MessageType.SUPER_CHAT_MESSAGE.getType())) {
            SuperChat superChat = new SuperChat(
                    message.getAuthorDetails().getDisplayName(),
                    message.getSnippet().getSuperChatDetails().getAmountDisplayString(),
                    message.getSnippet().getSuperChatDetails().getUserComment(),
                    videoID,
                    message.getSnippet().getPublishedAt().toString(),
                    message
            );
            for (YouTubeEventListener listener : listeners) {
                listener.onSuperChat(superChat);
            }
        } else if (message.getSnippet().getType().equals(MessageType.SUPER_STICKER_MESSAGE.getType())) {
            SuperSticker superSticker = new SuperSticker(
                    message.getAuthorDetails().getDisplayName(),
                    message.getSnippet().getSuperStickerDetails().getAmountDisplayString(),
                    message.getSnippet().getSuperStickerDetails().getSuperStickerMetadata().getStickerId(),
                    videoID,
                    message.getSnippet().getPublishedAt().toString(),
                    message
            );
            for (YouTubeEventListener listener : listeners) {
                listener.onSuperSticker(superSticker);
            }
        } else if (message.getSnippet().getType().equals(MessageType.TEXT_MESSAGE.getType())) {
            String contents = message.getSnippet().getDisplayMessage();
            for (YouTubeEventListener listener : listeners) {
                listener.onChat(new Chatting(message.getAuthorDetails().getDisplayName(), contents, videoID, message));
            }
        }
    }

    private void processError(Exception e) {
        for (YouTubeEventListener listener : listeners) {
            listener.onError(e);
        }
    }

    public YouTubeInfo channelInfo() {
        return YouTubeInfo.from(videoID, api);
    }
}