package org.home.sziolkow.chat;

/**
 * Created by slawek on 09/07/15.
 */
public class DataReadEvent {
    final String message;

    public DataReadEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
