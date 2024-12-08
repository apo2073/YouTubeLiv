package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;

public class SuperChat {
    private final String author;
    private final String amount;
    private final String message;
    private final String timestamp;
    private final LiveChatMessage liveChatMessage;
    private final String videoId;

    public SuperChat(String author, String amount, String message, String videoId, String timestamp, LiveChatMessage liveChatMessage) {
        this.amount = amount;
        this.message = message;
        this.timestamp = timestamp;
        this.liveChatMessage = liveChatMessage;
        this.videoId=videoId;
        this.author=author;
    }
    public String getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
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
