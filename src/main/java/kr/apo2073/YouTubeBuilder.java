package kr.apo2073;

import kr.apo2073.exception.NullVideoID;
import kr.apo2073.listener.YouTubeEventListener;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class YouTubeBuilder {
    String API_KEY="AIzaSyBpMcjduOo5VbaWa-ptNGuGsG323gaop60";
    String VIDEO_ID;
    List<YouTubeEventListener> listeners=new ArrayList<>();
    long pollingInterval = 5000;
    boolean isDebug=false;

    public YouTubeBuilder setApiKey(String key) {this.API_KEY=key;return this;}
    public YouTubeBuilder setVIDEO_ID(String id) {this.VIDEO_ID =id; return this;}
    public YouTubeBuilder setDebug(boolean debug) {this.isDebug=debug; return this;}
    public YouTubeBuilder setPollingInterval(long interval) {this.pollingInterval=interval;return this;}
    public YouTubeBuilder addListener(YouTubeEventListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public Youtube build() {
        if (VIDEO_ID !=null) {
            try {
                return new Youtube(this);
            } catch (GeneralSecurityException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new NullVideoID();
        }
    }
}
