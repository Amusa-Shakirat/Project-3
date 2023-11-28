package com.Shakirat.userDemo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Messages {

    File messageFile = new File("message.txt");

    File logFile = new File("log.txt");

    @PostMapping("/create-message")
    public String postMessage(@RequestBody String msg) {
        try (BufferedWriter writeMessage = new BufferedWriter(new FileWriter(messageFile, true));
             BufferedWriter writeLog = new BufferedWriter(new FileWriter(logFile, true));) {

            // writing to the message.txt
            writeMessage.write(msg + "# \n");
            // logging to log.txt

            writeLog.write("New massage posted, " + "# \n");


        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }

        return "Message Posted Successfully";
    }

    @GetMapping("/get-messages")
    public String getMessage(){

        StringBuilder stringBuilder = new StringBuilder();
        String message;
        try(BufferedReader messageReader = new BufferedReader(new FileReader(messageFile));){

            while( ( message = messageReader.readLine() ) != null ) {
                stringBuilder.append(message);
            }



        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        return stringBuilder.toString();
    }

    @GetMapping("/message-count")
    public int messageCount(){

        int messageCount = 0;
        List<String> myList = new ArrayList<String>();
        String read;
        String myRead = null;
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader messageCounter = new BufferedReader(new FileReader(messageFile));){

            while( (read = messageCounter.readLine()) != null) {

                stringBuilder.append(read);
                myRead = stringBuilder.toString();

            }

            myList  = Arrays.asList(myRead.split("#"));
            messageCount = myList.size() - 1;


        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        return  messageCount;

    }

}



