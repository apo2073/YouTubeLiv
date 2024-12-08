package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;

public class Chatting {
    private final String author;
    private final String message;
    private final LiveChatMessage liveChatMessage;
    private final String videoId;

    public Chatting(String author, String message, String videoId, LiveChatMessage liveChatMessage) {
        this.author = author;
        this.message = message;
        this.liveChatMessage = liveChatMessage;
        this.videoId=videoId;
    }

    public String getMessage() {
        return message;
    }

    public String getVideoId() {
        return videoId;
    }

    public Author author() {
        return new Author(author, liveChatMessage);
    }
}
