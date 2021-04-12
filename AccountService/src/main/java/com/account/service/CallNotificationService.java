package com.account.service;

import com.account.model.Notification;
import com.account.model.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

@Service
@PropertySource("classpath:Configurations.properties")
public class CallNotificationService {
    @Autowired
    RestTemplate restClient;
    @Value("${NotificationController.CreateNotification}")
    String url;

    public void createNotification(Notification notification){
//        Properties prop = new Properties();
//        String propFileName = "src/main/resources/Configurations.properties";
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//        String url = prop.getProperty("NotificationController.CreateNotification");
        Long id = restClient.postForObject(url, notification, Long.class);
        System.out.println("Created a notification"+ id);
    }
    public NotificationResponse getNotifications(String userId){
        return restClient.getForObject(url+userId, NotificationResponse.class);
    }
}
