package kr.apo2073.ytliv.listener;

import kr.apo2073.ytliv.data.Chatting;
import kr.apo2073.ytliv.data.SuperChat;
import kr.apo2073.ytliv.data.SuperSticker;

public interface YouTubeEventListener {
    default void onChat(Chatting chat) {}
    default void onSuperChat(SuperChat superChat) {}
    default void onSuperSticker(SuperSticker superSticker) {}
    default void onError(Exception e) {}
}
