package kr.apo2073.ytliv.utilities;

public class Debugger {
    public boolean isDebug=false;
    public void log(String message) {
        if (!isDebug) return;
        System.out.printf("[ YoutubeLiv ] %s%n",message);
    }
}
