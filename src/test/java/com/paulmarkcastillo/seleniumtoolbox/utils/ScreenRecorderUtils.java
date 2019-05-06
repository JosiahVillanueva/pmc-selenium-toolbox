package com.paulmarkcastillo.seleniumtoolbox.utils;

import java.io.File;
import java.io.IOException;

public class ScreenRecorderUtils {

    private Process process;
    private File file;

    public ScreenRecorderUtils(File file) {
        this.file = file;
    }

    public void startRecording() {
        try {
            String command = "ffmpeg -y -threads 0 -f avfoundation -i 0 -r 12 -map 0:v:0 -map_metadata -1 -c:v libx264 -profile:v baseline -level 3.0 -preset veryslow -crf 1 -pix_fmt yuv420p -tune zerolatency -movflags +faststart " + file.getPath();
            System.out.println("[SCREEN] Start Record: " + command);
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (process != null) {
            System.out.println("[SCREEN] Stop Record");
            process.destroy();
        }
    }

    public void deleteRecording() {
        System.out.println("[SCREEN] Delete Record");
        file.delete();
    }
}
