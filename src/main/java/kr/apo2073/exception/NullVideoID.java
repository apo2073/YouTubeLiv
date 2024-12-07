package kr.apo2073.exception;

public class NullVideoID extends RuntimeException {
    public NullVideoID() {
        super("비디오 아이디가 설정되지 않았습니다.");
    }
}
