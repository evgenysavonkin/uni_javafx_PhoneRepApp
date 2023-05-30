package com.example.lab5app;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class FileActions {
    public static RandomAccessFile file = null;
    public static final String FILE_NAME = "user-info.txt";
    public static void openFile(){
        try{
            file = new RandomAccessFile(FILE_NAME, "rw");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
