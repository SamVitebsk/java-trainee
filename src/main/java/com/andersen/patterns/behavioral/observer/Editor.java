package com.andersen.patterns.behavioral.observer;

import java.io.File;
import java.util.Objects;

public class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (Objects.nonNull(file)) {
            events.notify("open", file);
        } else {
            throw new Exception("Open a file");
        }
    }
}
