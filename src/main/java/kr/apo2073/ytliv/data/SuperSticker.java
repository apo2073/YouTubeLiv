package kr.apo2073.ytliv.data;

import com.google.api.services.youtube.model.LiveChatMessage;
import com.google.api.services.youtube.model.LiveChatMessageAuthorDetails;

public class SuperSticker {
    private final String amount;
    private final String stickerID;
    private final String timestamp;
    private final LiveChatMessage liveChatMessage;

    public SuperSticker(String amount, String content, String timestamp, LiveChatMessage liveChatMessage) {
        this.amount = amount;
        this.stickerID = content;
        this.timestamp = timestamp;
        this.liveChatMessage = liveChatMessage;
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

    public LiveChatMessageAuthorDetails author() {return liveChatMessage.getAuthorDetails();}
}
