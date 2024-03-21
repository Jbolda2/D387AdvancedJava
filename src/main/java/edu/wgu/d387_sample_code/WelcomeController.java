
package edu.wgu.d387_sample_code;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static edu.wgu.d387_sample_code.Welcome.welcomeMessage;
import static edu.wgu.d387_sample_code.Welcome.welcomeMessages;
import static java.util.concurrent.Executors.newFixedThreadPool;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WelcomeController {

    static ExecutorService messageExecutor=newFixedThreadPool(5);

public Welcome welcome;
public class Multithreading extends Thread {

    private String property;
    public Multithreading(String propertyLink) {
        this.property = propertyLink;
    }

    public String getProperty(){
        return property;
    }

    @Override
    public void run(){
    Properties properties=new Properties();

        messageExecutor.execute(() -> {
            try {


                InputStream stream = new ClassPathResource(property).getInputStream();
                properties.load(stream);
                String message = properties.getProperty("welcome");
                welcomeMessage = welcomeMessage + message + "\n";

                //System.out.println(properties.getProperty("welcome"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    /*messageExecutor.execute(()-> {
        try {
            InputStream stream = new ClassPathResource("welcome_fr_CA.properties").getInputStream();
            properties.load(stream);
            //System.out.println(properties.getProperty("welcome"));
            welcome.setFrench(properties.getProperty("welcome"));
            //System.out.println(Welcome.getFrench());
        } catch (Exception e) {
            e.printStackTrace();
        }
    });*/

}
}

@RequestMapping(value = "/welcome")
    public ResponseEntity<String> showWelcome(){

    Multithreading threadOne = new Multithreading("welcome_fr_CA.properties");
    Multithreading threadTwo = new Multithreading("welcome_en_US.properties");

    threadOne.start();
    threadTwo.start();



    return new ResponseEntity<String> (welcomeMessage, HttpStatus.OK);
}


}


