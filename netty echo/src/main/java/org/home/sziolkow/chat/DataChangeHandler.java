package org.home.sziolkow.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slawek on 09/07/15.
 */
public class DataChangeHandler {

    private static List<DataChangeListener> listeners = new ArrayList<>();

    public static void registerDataChangeListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    public static void fireChangeEvent(DataReadEvent event) {
        for(DataChangeListener listener:listeners) {
            listener.dataChangeEnent(event);
        }
    }
}
