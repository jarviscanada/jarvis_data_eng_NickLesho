package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TwitterService implements Service {
    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao) {this.dao = dao;}

    @Override
    public Tweet postTweet(Tweet tweet) throws IllegalArgumentException {
        if (validatePostTweet(tweet)){
            return (Tweet) dao.create(tweet);
        } else {
            throw new IllegalArgumentException("tweet exceeds 140 characters");
        }

    }

    @Override
    public Tweet showTweet(String id, String[] fields) throws IllegalArgumentException {
        Tweet tweet = (Tweet) dao.findById(id);
        ArrayList<String> validFields = ["created_at", "id", "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count", "favorited", "retweeted"];
        if (tweet == null) {
            throw new IllegalArgumentException("invalid ID");
        }
        for (String field : fields) {
            if (!(validFields.contains(field))){
                throw new IllegalArgumentException("invalid parameters");
            }
        }
        ArrayList<String> fieldList = new ArrayList<>(Arrays.asList(fields));
        if (!(fieldList.contains("created_at"))) {
            tweet.setCreatedAt(null);
        }
        if (!(fieldList.contains("id"))) {
            tweet.setId(null);
        }
        if (!(fieldList.contains("id_str"))) {
            tweet.setIdStr(null);
        }
        if (!(fieldList.contains("text"))) {
            tweet.setText(null);
        }
        if (!(fieldList.contains("entities"))) {
            tweet.setEntities(null);
        }
        if (!(fieldList.contains("coordinates"))) {
            tweet.setCoordinates(null);
        }
        if (!(fieldList.contains("retweet_count"))) {
            tweet.setRetweetCount(null);
        }
        if (!(fieldList.contains("favorite_count"))) {
            tweet.setFavoriteCount(null);
        }
        if (!(fieldList.contains("favorited"))) {
            tweet.setFavorited(null);
        }
        if (!(fieldList.contains("retweeted"))) {
            tweet.setRetweeted(null);
        }

        return tweet;
    }

    @Override
    public Tweet deleteTweet(String[] ids) throws IllegalArgumentException {
        ArrayList<String> idList = new ArrayList<>();
        for (String id : ids) {
            Tweet tweet = (Tweet) dao.findById(id);
            if (tweet == null) {
                throw new IllegalArgumentException("invalid ID");
            } else {
                idList.add(id);
            }
        }
        return idList;

    }

    private boolean validatePostTweet(Tweet tweet) {
        float lon = tweet.getCoordinates().getCoordinates().get(0);
        float lat = tweet.getCoordinates().getCoordinates().get(1);
        boolean lonRange = lon > -180.00 && lon < 180.00;
        boolean latRange = lat > -90.00 && lat < 90.00;
        return tweet.getText().length() <= 140 && lonRange && latRange;
    }
}
