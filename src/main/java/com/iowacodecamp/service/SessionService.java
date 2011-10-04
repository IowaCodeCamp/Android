package com.iowacodecamp.service;

import com.iowacodecamp.entity.Session;
import com.iowacodecamp.entity.SessionDeserializer;
import com.iowacodecamp.webservice.SessionWebService;

import java.util.List;

public class SessionService {

    private SessionWebService service;
    private SessionDeserializer deserializer;

    public SessionService() {
        service = new SessionWebService();
        deserializer = new SessionDeserializer();
    }

    public List<Session> getListOfSessions() {
        String json = service.getListOfSessions();
        List<Session> sessions = deserializer.GetSessionsFromJson(json);

        return sessions;
    }
}
