package CW2;

import java.io.*;
import java.util.Map;
import java.util.NoSuchElementException;

public class WorkWithFile implements Runnable {
    private final File file;

    public WorkWithFile(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int sz = dataInputStream.readInt();
            byte[] data = new byte[sz];
            dataInputStream.read(data);
            int even = dataInputStream.readInt();
            int part = dataInputStream.readInt();

            if (checkEven(data) == even) {
                synchronized (Main.photoParts) {
                    Main.photoParts.put(part, data);
                }
            } else {
                System.out.println("Четность не совпадает");
                throw new NoSuchElementException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Функция для подсчета количества единиц в двоичном представлении числа
    public static int countOnesInBinary(byte b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if ((b & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }

    //функция проверки четности
    public static int checkEven(byte[] data) {
        int controlSum = 0;
        for (byte b : data) {
            int count = countOnesInBinary(b);
            controlSum += count;
        }
        return controlSum % 2;

    }
}




