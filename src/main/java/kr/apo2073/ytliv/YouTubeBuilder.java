package kr.apo2073.ytliv;

import kr.apo2073.ytliv.exception.NullVideoID;
import kr.apo2073.ytliv.listener.YouTubeEventListener;
import kr.apo2073.ytliv.utilities.Debugger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class YouTubeBuilder {
    public String API_KEY="KEY";
    public String VIDEO_ID;
    public List<YouTubeEventListener> listeners=new ArrayList<>();
    public long pollingInterval = 5000;
    public boolean isDebug=false;
    public int chat_length=0;
    private Debugger debugger=new Debugger();

    public YouTubeBuilder setApiKey(String key) {this.API_KEY=key;return this;}
    public YouTubeBuilder setVideoId(String id) {this.VIDEO_ID =id; return this;}
    public YouTubeBuilder setDebug(boolean debug) {this.isDebug=debug; debugger.isDebug=debug ;return this;}
    public YouTubeBuilder setPollingInterval(long interval) {this.pollingInterval=interval;return this;}
    public YouTubeBuilder addListener(YouTubeEventListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public Youtube build() {
        debugger.log("new builder built");
        if (VIDEO_ID !=null) {
            try {
                debugger.log("build successful");
                return new Youtube(this);
            } catch (GeneralSecurityException | IOException e) {
                debugger.log("build fail");
                throw new RuntimeException(e);
            }
        } else {
            debugger.log("cant find video");
            throw new NullVideoID();
        }
    }
}
