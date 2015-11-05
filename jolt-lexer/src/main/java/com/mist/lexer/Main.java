package com.mist.lexer;

import java.io.*;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            try {
                Reader reader;
                File file = new File(args[0]);
                if (file.exists()) {
                    reader = new FileReader(file);
                } else {
                    reader = new InputStreamReader(
                            Main.class.getClassLoader().getResourceAsStream(args[0]));
                }
                new Lexer(reader).withOutputStream(System.out).lexerize();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Must specify at least 1 parameter");
        }
    }
}
