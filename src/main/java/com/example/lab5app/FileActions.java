package com.example.lab5app;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class FileActions {
    public static RandomAccessFile file = null;
    public static String file_name = "test.txt";
    public static void openfile(){
        try{
            file = new RandomAccessFile(file_name, "rw");
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
