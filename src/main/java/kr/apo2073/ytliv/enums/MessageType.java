package kr.apo2073.ytliv.enums;

public enum MessageType {
    TEXT_MESSAGE("textMessageEvent"),
    SUPER_CHAT_MESSAGE("superChatEvent"),
    SUPER_STICKER_MESSAGE("superStickerEvent");

    private final String type;
    MessageType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public static MessageType fromType(String type) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.getType().equalsIgnoreCase(type)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}
