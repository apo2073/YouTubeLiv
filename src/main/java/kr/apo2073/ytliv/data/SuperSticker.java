package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;

public class SuperSticker {
    private final String author;
    private final String amount;
    private final String stickerID;
    private final String timestamp;
    private final LiveChatMessage liveChatMessage;
    private final String videoId;

    public SuperSticker(String author, String amount, String content, String videoId, String timestamp, LiveChatMessage liveChatMessage) {
        this.amount = amount;
        this.stickerID = content;
        this.timestamp = timestamp;
        this.liveChatMessage = liveChatMessage;
        this.videoId=videoId;
        this.author=author;
    }

    public String getAmount() {
        return amount;
    }

    public String getStickerID() {
        return stickerID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getVideoId() {
        return videoId;
    }

    public Author author() {
        return new Author(author, liveChatMessage);
    }
}
