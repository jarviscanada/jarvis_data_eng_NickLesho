package ca.jrvs.apps.twitter.example.dto;

import java.net.http.HttpResponse;
import java.util.Arrays;
import oauth.signpost;

public class TwitterApiTest {

    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception {

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "today is a good day";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));

        consumer.sign(request);

        System.out.println("Http Request Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = HttpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}

