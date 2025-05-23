[![](https://jitpack.io/v/apo2073/YouTubeLiv.svg)](https://jitpack.io/#apo2073/YouTubeLiv)

### __Better Easy YouTube Java API__

---

## Dependency
**gradle.kts**
```gradle
repositories {
    mavenCentral()
    maven("https://jitpack.io") {
        name = "jitpack"
    }
}	
dependencies {
    implementation("com.github.apo2073:YouTubeLiv:Tag")
}
```

---

## Example
```java
public class Main {
    public static void main(String[] args) {
        Youtube youtube = new YouTubeBuilder()
                .setApiKey("API-KEY")
                .setVideoId("Live-Video-Id") //https://www.youtube.com/watch?v=(here)
                .addListener(new YouTubeEventListener() {
                    @Override
                    public void onChat(Chatting chat) {
                        System.out.println(chat.author().getDisplayName()+": "+chat.getMessage());
                    }
                }).build();
        
        YouTubeInfo info = youtube.channelInfo();
        System.out.println(info.getChannelName()); // Get Channel Name
        System.out.println(info.getSubscriptionCount()); // Get Channel Subscription Count
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        youtube.stop(); // Stop
    }
}
```
---

[Get Api Key](https://console.cloud.google.com/apis/library/youtube.googleapis.com?hl=ko&inv=1&invt=Abjeiw&project=just-landing-330610) 
[Example](https://github.com/apo2073/YoutubeLivExample)
