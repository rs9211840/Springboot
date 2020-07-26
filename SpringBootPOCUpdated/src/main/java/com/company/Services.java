package com.company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class Services {

    public String helloWorld()
    {
        return "Hello Welcome to Spring World";
    }


    public String helloWorld(String str)
    {
        return "Hello " + str +" Welcome to Spring World";
    }

    public ResponseEntity<Object> listFiles()
    {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        System.out.println(currentDirectory);
        File directoryPath = new File(currentDirectory);
        File filesList[] = directoryPath.listFiles();
        String s="<ol>";
        if(filesList.length > 0) {
            for (int i = 0; i < filesList.length; i++) {
                s = s + "<li>" + filesList[i] + "</li>";
            }
            s = s + "</ol>";
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("No File Exist at :"+currentDirectory, HttpStatus.OK);
    }

    public ResponseEntity<Object> createDirectory(String filename)
    {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        System.out.println(currentDirectory);
        currentDirectory = currentDirectory + filename;
        File file;
        file = new File(currentDirectory);
        String msg;
        if(file.mkdir()){
            msg = " Directory created successfully";
        }else{
            msg = " Directory could not be created successfully";
        }
        return   new ResponseEntity<>(filename+msg, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> createFile(String filename) {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        System.out.println(currentDirectory);
        currentDirectory = currentDirectory + filename;
        File file;
        file = new File(currentDirectory);
        String msg="";
        try {
            if (file.createNewFile())
                msg = "File created with name: " + file.getName();
            else
                msg = file.getName() + " already exist at the location";
        } catch (Exception e)
        {}

        return   new ResponseEntity<>(filename+msg, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> createFileAndWriteToFile(String filename,String content) {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        currentDirectory = currentDirectory + filename;
        System.out.println(currentDirectory);
        File file = new File(currentDirectory);

        String msg="";
        try {
            if (file.createNewFile())
                msg = "File created with name: " + file.getName();
            else
                msg = file.getName() + " already exist at the location and ";

            FileWriter myWriter = new FileWriter(currentDirectory,true);
            myWriter.write(content);
            myWriter.close();
                msg =msg +" Written "+content +" in the file at location "+ currentDirectory;
        } catch (Exception e)
        {}

        return   new ResponseEntity<>(filename+msg, HttpStatus.CREATED);
    }


    public ResponseEntity<Object> readFile(String filename) {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        currentDirectory = currentDirectory + filename;
        System.out.println(currentDirectory);


        String msg="";
        try {
            File file = new File(currentDirectory);
            Scanner myReader = new Scanner(file);
            if (!file.createNewFile()) {
                while (myReader.hasNextLine()) {
                    msg = msg + myReader.nextLine();
                }
                myReader.close();
            }
            else
                msg = file.getName() + " already exist at the location";


        } catch (Exception e)
        {}

        return   new ResponseEntity<>(msg, HttpStatus.OK);
    }


    public ResponseEntity<Object> deleteFile(String filename) {
        String currentDirectory = "/home/ra.singh1/IdeaProjects/SpringBootPOCUpdated/Practice/";
        currentDirectory = currentDirectory + filename;
        System.out.println(currentDirectory);


        String msg="";
        try {
            File file = new File(currentDirectory);
            if (file.exists() )
                if (deleteFile(file)) {
                    msg = "File/Folder  Deleted Successfully";
                }
                else
                    msg ="Could not delete the file";
            else
                msg ="File does not exist";

        } catch (Exception e)
        {}

        return   new ResponseEntity<>(msg, HttpStatus.OK);
    }

    public boolean deleteFile(File dir)
    {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    deleteFile(aFile);
                }
            }
            return dir.delete();
        } else {
            return  dir.delete();
        }
    }


}
