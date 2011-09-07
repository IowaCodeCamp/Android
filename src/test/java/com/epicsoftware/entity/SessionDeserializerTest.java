package com.epicsoftware.entity;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class SessionDeserializerTest {
    private String validJson = "{\"d\":{\"success\":true,\"msg\":null,\"data\":[{\"session\":\"First Session\",\"time\":\"9:00 AM - 10:30 AM\",\"desc\":\"very fun session\",\"speaker\":{\"name\":\"Chris Missal\",\"title\":\"Rockstar\",\"bio\":\"my hero\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Next Session\",\"time\":\"9:00 AM - 10:30 AM\",\"desc\":\"not a good session\",\"speaker\":{\"name\":\"Toran Billups\",\"title\":\"Dude\",\"bio\":\"kid\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Last Session\",\"time\":\"9:00 AM - 10:30 AM\",\"desc\":\"final session\",\"speaker\":{\"name\":\"Keith Dahlby\",\"title\":\"Ninja\",\"bio\":\"rx ftw\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"jQuery\",\"time\":\"11:00 AM - 12:30 PM\",\"desc\":\"jquery dudes\",\"speaker\":{\"name\":\"Chris Missal\",\"title\":\"Rockstar\",\"bio\":\"my hero\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Mobile\",\"time\":\"11:00 AM - 12:30 PM\",\"desc\":\"mobile dudes\",\"speaker\":{\"name\":\"Toran Billups\",\"title\":\"Dude\",\"bio\":\"kid\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Rx\",\"time\":\"11:00 AM - 12:30 PM\",\"desc\":\"rx dudes\",\"speaker\":{\"name\":\"Keith Dahlby\",\"title\":\"Ninja\",\"bio\":\"keith son\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Wrap it\",\"time\":\"1:30 AM - 3:00 PM\",\"desc\":\"wrap session\",\"speaker\":{\"name\":\"Chris Missal\",\"title\":\"Rockstar\",\"bio\":\"my hero\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Slice it\",\"time\":\"1:30 AM - 3:00 PM\",\"desc\":\"slice session\",\"speaker\":{\"name\":\"Toran Billups\",\"title\":\"Dude\",\"bio\":\"my hero\",\"img\":\"toranbillups.jpg\"}},{\"session\":\"Dice it\",\"time\":\"1:30 AM - 3:00 PM\",\"desc\":\"dice session\",\"speaker\":{\"name\":\"Keith Dahlby\",\"title\":\"Ninja\",\"bio\":\"rx ftw\",\"img\":\"toranbillups.jpg\"}}]}}";

    @Test
    public void buildsListOfSessionsWithValidInput() {
        SessionDeserializer sut = new SessionDeserializer();

        List<Session> sessions = sut.GetSessionsFromJson(validJson);

        Assert.assertEquals(sessions.size(), 9);
    }

    @Test
    public void sessionsBuiltDuringDeserializationHaveCorrectValues() {
        SessionDeserializer sut = new SessionDeserializer();

        List<Session> sessions = sut.GetSessionsFromJson(validJson);

        Assert.assertEquals(sessions.get(0).getSession(), "First Session");
        Assert.assertEquals(sessions.get(0).getTime(), "9:00 AM - 10:30 AM");
        Assert.assertEquals(sessions.get(0).getDesc(), "very fun session");
        Assert.assertEquals(sessions.get(0).getSpeaker().getName(), "Chris Missal");
        Assert.assertEquals(sessions.get(0).getSpeaker().getTitle(), "Rockstar");
        Assert.assertEquals(sessions.get(0).getSpeaker().getBio(), "my hero");
        Assert.assertEquals(sessions.get(0).getSpeaker().getImg(), "toranbillups.jpg");
    }

    @Test
    public void emptyListOfSessionsReturnedWhenBrokenJsonIsParsed() {
        SessionDeserializer sut = new SessionDeserializer();

        List<Session> sessions = sut.GetSessionsFromJson("ata\":[{\"session\":\"Fi");

        Assert.assertEquals(sessions.size(), 0);
    }
}
