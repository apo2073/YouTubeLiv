import kr.apo2073.YouTubeBuilder;
import kr.apo2073.Youtube;
import kr.apo2073.data.Chatting;
import kr.apo2073.listener.YouTubeEventListener;

public class Main {
    public static void main(String[] args) {
        Youtube youtube=new YouTubeBuilder()
                .setVIDEO_ID("CYhGhs0Ismk")
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