package edu.wgu.d387_sample_code;

import org.springframework.beans.factory.annotation.Value;


import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Welcome {


    public static String[] welcomeMessages = new String[2];

    public static String welcomeMessage = "";

    public static void setWelcomeMessages(String welcomeMessage, int i) {
        welcomeMessages[i] = welcomeMessage;
    }
}


