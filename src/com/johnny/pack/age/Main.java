package com.johnny.pack.age;

import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        createNewFile();
        lineSpace();
        showDirContents();
        lineSpace();
        showOnlyType();
        lineSpace();
        treeWalker();
    }

    private static void treeWalker() {
        System.out.println("tree-walka tree-walka b'dah!");
        Path start = Paths.get("C:\\Users\\johnr\\Desktop\\java_dummies");
        MyFileVisitor visitor = new MyFileVisitor();
        try {
            Files.walkFileTree(start, visitor);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void lineSpace() {
        System.out.println("\n");
    }

    private static void showOnlyType() {
        System.out.println("Show certain file types of directory");
        Path c = Paths.get("C:\\Users\\johnr\\Desktop\\java_dummies\\knightMove");
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(c, "*.java");
            for (Path entry : stream) {
                System.out.println(entry.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showDirContents() {
        System.out.println("Show the directory contents");
        Path c = Paths.get("C:\\Users\\johnr\\Desktop\\java_dummies");
        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(c);
            for(Path entry : stream){
                System.out.println(entry.toString());
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createNewFile() {
        System.out.println("Create a new file if it does not already exist");
        String startFile = "C:\\Users\\johnr\\Desktop\\java_dummies\\test_file.txt";
        Path p = Paths.get(startFile);

        if(!Files.exists(p)){
            System.out.println("The input file does not exist!");
            try{
                Files.createFile(p);
                System.out.println("File created");
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("File already exists!");
        }
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path>{
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            System.out.println(file.toString());
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFileFailed(Path file, BasicFileAttributes attr){
            System.out.println(file.toString() + " COULD NOT ACCESS!");
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path dir, IOException e){
            System.out.println(dir.toString() + " COULD NOT ACCESS!");
            return FileVisitResult.CONTINUE;
        }


    }
}
