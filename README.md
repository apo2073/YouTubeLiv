[![](https://jitpack.io/v/apo2073/YouTubeLiv.svg)](https://jitpack.io/#apo2073/YouTubeLiv)

```gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.apo2073:YouTubeLiv:Tag'
	}
```

---

## Example
```java
public class Main {
    public static void main(String[] args) {
        Youtube youtube=new YouTubeBuilder()
                .setVIDEO_ID("Youtube video id") //https://www.youtube.com/watch?v=(here)
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
```
