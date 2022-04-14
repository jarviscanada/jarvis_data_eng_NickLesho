package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import com.xkcoding.http.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class TwitterController {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController(Service service) {this.service = service;}

    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String tweet_txt = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);
        if (coordArray.length != 2 || StringUtil.isEmpty(tweet_txt)) {
            throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        Double lat = null;
        Double lon = null;
        try {
            lat = Double.parseDouble(coordArray[0]);
            lon = Double.parseDouble(coordArray[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"", e);
        }

        Tweet postTweet = buildTweet(tweet_txt, lon, lat);
        return service.postTweet(postTweet);
    }

    public Tweet showTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"id\" \"fields\"");
        }
        String id = args[1];
        String field = args[2];
        String[] fieldArray = field.split(COORD_SEP);
        return service.showTweet(id, fieldArray);
    }

}
