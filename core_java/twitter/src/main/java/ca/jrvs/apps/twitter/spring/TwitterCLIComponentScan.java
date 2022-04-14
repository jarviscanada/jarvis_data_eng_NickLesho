package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.controller.TwitterCLIApp;
import org.apache.catalina.core.ApplicationContext;

@ComponentScan(value= "ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIComponentScan.class);
        TwitterCLIApp app = context.getBean(TwitterCliApp.class);
        app.run(args);
    }
}
