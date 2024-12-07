package kr.apo2073.listener;

import kr.apo2073.data.Chatting;
import kr.apo2073.data.SuperChat;
import kr.apo2073.data.SuperSticker;

public interface YouTubeEventListener {
    default void onChat(Chatting chat) {}
    default void onSuperChat(SuperChat superChat) {}
    default void onSuperSticker(SuperSticker superSticker) {}
    default void onError(Exception e) {}
}
