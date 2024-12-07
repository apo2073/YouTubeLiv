package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;
import com.google.api.services.youtube.model.LiveChatMessageAuthorDetails;

public class SuperChat {
    private final String amount;
    private final String message;
    private final String timestamp;
    private final LiveChatMessage liveChatMessage;

    public SuperChat(String amount, String message, String timestamp, LiveChatMessage liveChatMessage) {
        this.amount = amount;
        this.message = message;
        this.timestamp = timestamp;
        this.liveChatMessage = liveChatMessage;
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

    public LiveChatMessageAuthorDetails author() {
        return liveChatMessage.getAuthorDetails();
    }
}
