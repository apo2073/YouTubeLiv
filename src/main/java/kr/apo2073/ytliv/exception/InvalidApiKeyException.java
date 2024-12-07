package kr.apo2073.ytliv.exception;

public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException() {
        super("Invalid API Key");
    }
}
