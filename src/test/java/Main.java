import kr.apo2073.ytliv.YouTubeBuilder;
import kr.apo2073.ytliv.Youtube;
import kr.apo2073.ytliv.data.Chatting;
import kr.apo2073.ytliv.listener.YouTubeEventListener;

public class Main {
    public static void main(String[] args) {
        Youtube youtube = new YouTubeBuilder()
                .setApiKey("API-KEY")
                .setVIDEO_ID("Live-Video-Id") //https://www.youtube.com/watch?v=(here)
                .addListener(new YouTubeEventListener() {
                    @Override
                    public void onChat(Chatting chat) {
                        System.out.println(chat.author().getDisplayName()+": "+chat.getMessage());
                    }
                }).build();

        Youtube.YouTubeInfo info = youtube.channelInfo();
        System.out.println(info.getChannelName()); // Get Channel Name
        System.out.println(info.getSubscriptionCount()); // Get Channel Subscription Count
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        youtube.stop();
        System.out.println("Stopped");
    }
}