package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.springframework.xd.shell.util.JsonUtil;
import org.springframework.cloud.fn.common.twitter.util.TweetUtil;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception {
        String hashTag = "#abc";
        String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TweetUtil.buildTweet(text, lon, lat));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "\"id\":1097607853932564480\",\n"
                + "\"id_str\":\"1097607853932564480\",\n"
                + "\text\":\"test with loc223\",\n"
                + "\"entities\":{\n"
                + "\"hashtags\":[],"
                + "\"user_mentions\":[]"
                + "},\n"
                + "\"coordinates\":null,"
                + "\"retweet_count\":0,\n"
                + "\"favorite_count\":0,\n"
                + "\"favorited\":false,\n"
                + "\"retweeted\":false\n"
                +"}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, lon, lat));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());

    }

    public void postTweet() throws Exception {
        String tweetJsonStr = "{\n"
                + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "\"id\":1097607853932564480\",\n"
                + "\"id_str\":\"1097607853932564480\",\n"
                + "\text\":\"test with loc223\",\n"
                + "\"entities\":{\n"
                + "\"hashtags\":[],"
                + "\"user_mentions\":[]"
                + "},\n"
                + "\"coordinates\":null,"
                + "\"retweet_count\":0,\n"
                + "\"favorite_count\":0,\n"
                + "\"favorited\":false,\n"
                + "\"retweeted\":false\n"
                +"}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(expectedTweet);
        Tweet tweet2 = spyDao.findById(tweet.getIdStr());
        assertEquals("same tweet id", tweet.getId(), tweet2.getId());
    }

    public void deleteTweet() throws Exception {
        String tweetJsonStr = "{\n"
                + "\"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "\"id\":1097607853932564480\",\n"
                + "\"id_str\":\"1097607853932564480\",\n"
                + "\text\":\"test with loc223\",\n"
                + "\"entities\":{\n"
                + "\"hashtags\":[],"
                + "\"user_mentions\":[]"
                + "},\n"
                + "\"coordinates\":null,"
                + "\"retweet_count\":0,\n"
                + "\"favorite_count\":0,\n"
                + "\"favorited\":false,\n"
                + "\"retweeted\":false\n"
                +"}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(expectedTweet);
        spyDao.deleteById(tweet.getIdStr());
        AssertNull("should be null", spyDao.findById(tweet.getIdStr());
    }

}