package CW2;

import java.io.*;
import java.util.*;

public class Main {
    public static Map<Integer, byte[]> photoParts = new HashMap<>();
    public static void main(String[] args) {
        try {
            String variant = "10";
            String path = "v" + variant;
            File folder = new File(path);
            File[] files = folder.listFiles();


            List<Thread> threads = new ArrayList<>();

            for (File file : files) {
                Thread thread = new Thread(new WorkWithFile(file));
                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }


            List<Integer> parts = new ArrayList<>(photoParts.keySet());
            Collections.sort(parts);

            try (FileOutputStream write = new FileOutputStream("v" + variant + ".png")) {
                for (int key : parts) {
                    write.write(photoParts.get(key));
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}