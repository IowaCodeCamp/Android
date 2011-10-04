package com.iowacodecamp.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SessionDeserializerTest {
    private String validJson = "{\"d\":{\"success\":true,\"msg\":null,\"data\":[{\"session\":\"Automation Tools Roundup\",\"time\":\"9:00 AM - 10:15 AM\",\"desc\":\"learn about automation\",\"room\":\"Davis Room\",\"speaker\":{\"web\":\"http://tr.im/boss\",\"location\":\"Des Moines, IA\",\"name\":\"Rob Reynolds\",\"bio\":\"Rob likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Working with HTTP on the iPhone\",\"time\":\"9:00 AM - 10:15 AM\",\"desc\":\"Interested in writing a simple iPhone app like I was just a few months ago? If this app does anything of interest it will no doubt communicate over http. In this session I'll be showing the basics required to do an http request and response in objective-c from the view point of a beginner / intermediate iPhone developer. This talk will feature a deep technical look at how you create an http GET request, POST request with data, parse JSON, parse simple HTML to find view state (webforms), store cookies for authentication and much more!\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://toranbillups.com\",\"location\":\"Des Moines, IA\",\"name\":\"Toran Billups\",\"bio\":\"Toran Billups is an ex-web developer turned aspiring mobile developer. When he isn't writing c#, java or objective-c you can find him spending time with his family as he attempts the mythical work-life balance\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Intro to Parallel Programming\",\"time\":\"9:00 AM - 10:15 AM\",\"desc\":\"think coding in parallel is hard?\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Jeff Brand\",\"bio\":\"jeff likes to code also\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Advanced ASP.NET MVC\",\"time\":\"9:00 AM - 10:15 AM\",\"desc\":\"learn about the latest aspnet mvc tech\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Javier Lozano\",\"bio\":\"javier likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Break\",\"time\":\"10:15 AM - 10:30 AM\",\"desc\":\"\",\"room\":\"\",\"speaker\":{\"web\":\"\",\"location\":\"\",\"name\":\"\",\"bio\":\"\",\"img\":\"\"}},{\"session\":\"Taking Your OOP Skills to the Next Level\",\"time\":\"10:30 AM - 11:45 AM\",\"desc\":\"what is all this oop stuff about anyway?\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Adam Barney\",\"bio\":\"adam likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"LINQ to Objects - Practical Examples\",\"time\":\"10:30 AM - 11:45 AM\",\"desc\":\"linq is awesome\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Nate Adams\",\"bio\":\"nate likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Using HTML5 Today\",\"time\":\"10:30 AM - 11:45 AM\",\"desc\":\"using html today\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"James Eggers\",\"bio\":\"james likes to write code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Managing Independent Projects\",\"time\":\"10:30 AM - 11:45 AM\",\"desc\":\"like having indi projects?\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Dylan Moonfire\",\"bio\":\"dylan likes to writes code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Lunch\",\"time\":\"11:45 AM - 12:45 PM\",\"desc\":\"\",\"room\":\"\",\"speaker\":{\"web\":\"\",\"location\":\"\",\"name\":\"\",\"bio\":\"\",\"img\":\"\"}},{\"session\":\"Open Coding Session #1\",\"time\":\"12:45 PM - 2:00 PM\",\"desc\":\"more coding sessions\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Adam Barney\",\"bio\":\"adam likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Open Coding Session #2\",\"time\":\"12:45 PM - 2:00 PM\",\"desc\":\"more coding sessions\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Nate Adams\",\"bio\":\"nate likes to code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Open Coding Session #3\",\"time\":\"12:45 PM - 2:00 PM\",\"desc\":\"more coding sessions\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"James Eggers\",\"bio\":\"james likes to write code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Open Coding Session #4\",\"time\":\"12:45 PM - 2:00 PM\",\"desc\":\"more coding sessions\",\"room\":\"Amana Room\",\"speaker\":{\"web\":\"http://tr.im\",\"location\":\"Des Moines, IA\",\"name\":\"Dylan Moonfire\",\"bio\":\"dylan likes to writes code\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Closing and Prizes\",\"time\":\"2:00 PM - 2:15 PM\",\"desc\":\"\",\"room\":\"\",\"speaker\":{\"web\":\"\",\"location\":\"\",\"name\":\"\",\"bio\":\"\",\"img\":\"\"}}]}}";
    private SessionDeserializer sut;

    @Before
    public void setUp() {
         sut = new SessionDeserializer();
    }

    @Test
    public void buildsListOfSessionsWithValidInput() {
        List<Session> sessions = sut.GetSessionsFromJson(validJson);

        Assert.assertEquals(sessions.size(), 15);
    }

    @Test
    public void sessionsBuiltDuringDeserializationHaveCorrectValuesForSession() {
        List<Session> sessions = sut.GetSessionsFromJson(validJson);

        Assert.assertEquals(sessions.get(0).getSession(), "Automation Tools Roundup");
        Assert.assertEquals(sessions.get(0).getTime(), "9:00 AM - 10:15 AM");
        Assert.assertEquals(sessions.get(0).getDesc(), "learn about automation");
        Assert.assertEquals(sessions.get(0).getRoom(), "Davis Room");
    }

    @Test
    public void sessionsBuiltDuringDeserializationHaveCorrectValuesForSpeaker() {
        List<Session> sessions = sut.GetSessionsFromJson(validJson);

        Assert.assertEquals(sessions.get(0).getSpeaker().getName(), "Rob Reynolds");
        Assert.assertEquals(sessions.get(0).getSpeaker().getBio(), "Rob likes to code");
        Assert.assertEquals(sessions.get(0).getSpeaker().getImg(), "toranbillups.jpg");
        Assert.assertEquals(sessions.get(0).getSpeaker().getLocation(), "Des Moines, IA");
        Assert.assertEquals(sessions.get(0).getSpeaker().getWeb(), "http://tr.im/boss");
    }

    @Test
    public void emptyListOfSessionsReturnedWhenBrokenJsonIsParsed() {
        List<Session> sessions = sut.GetSessionsFromJson("ata\":[{\"session\":\"Fi");

        Assert.assertEquals(sessions.size(), 0);
    }
}
