package edu.wgu.d387_sample_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/announce")
public class TimeZoneController {

    final private String time = "12:00PM EST";

    final private String format = "hh:mma z";
    private final SimpleDateFormat formatTime = new SimpleDateFormat(format);

    String presentation = "";

    Date newTime = formatTime.parse(time);

    public TimeZoneController() throws ParseException {
    }

    public String convertTheTime(String inputZone) {
        TimeZone timeZone = TimeZone.getTimeZone(inputZone);
        formatTime.setTimeZone(timeZone);
        return formatTime.format(newTime);
    }


    @RequestMapping(value = "/presentation")
    public ResponseEntity<String> showPresentation() throws InterruptedException, ParseException {

        String universal = "UTC";
        String mountain = "MST";
        String eastern = "EST";

        eastern = convertTheTime(eastern);
        mountain = convertTheTime(mountain);
        universal = convertTheTime(universal);

        presentation = "Join us for an online live presentation held at the Landon Hotel on March 29th:"+ eastern + " " + mountain + " " + universal;

        return new ResponseEntity<String>(presentation, HttpStatus.OK);
    }

}



