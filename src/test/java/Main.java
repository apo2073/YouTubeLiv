import kr.apo2073.ytliv.YouTubeBuilder;
import kr.apo2073.ytliv.Youtube;
import kr.apo2073.ytliv.data.Chatting;
import kr.apo2073.ytliv.listener.YouTubeEventListener;

public class Main {
    public static void main(String[] args) {
        Youtube youtube=new YouTubeBuilder()
                .setApiKey("API KEY")
                .setVIDEO_ID("Video id")
                .addListener(new YouTubeEventListener() {
                    @Override
                    public void onChat(Chatting chat) {
                        System.out.println(chat.author().getDisplayName()+": "+chat.getMessage());
                    }
                }).build();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        youtube.stop();
        System.out.println("Stopped");
    }
}