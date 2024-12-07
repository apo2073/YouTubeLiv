package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;
import com.google.api.services.youtube.model.LiveChatMessageAuthorDetails;

public class Chatting {
    private final String message;
    private final LiveChatMessage liveChatMessage;

    public Chatting(String message, LiveChatMessage liveChatMessage) {
        this.message = message;
        this.liveChatMessage = liveChatMessage;
    }

    public String getMessage() {
        return message;
    }
    public LiveChatMessageAuthorDetails author() {
        return liveChatMessage.getAuthorDetails();
    }
}
