package com.epicsoftware.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SessionDeserializer {
    public List<Session> GetSessionsFromJson(String json) {
        ArrayList<Session> sessions = new ArrayList<Session>();

        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONObject a = (JSONObject) jsonObj.get("d");
            JSONArray data = (JSONArray) a.get("data");

            for (int i = 0; i < data.length(); i++) {
                Session session = new Session();

                JSONObject item = (JSONObject) data.get(i);

                session.setTime(String.valueOf(item.get("time")).trim());
                session.setSession(String.valueOf(item.get("session")).trim());
                session.setDesc(String.valueOf(item.get("desc")).trim());

                JSONObject speakerJsonObj = (JSONObject) item.get("speaker");
                Speaker speaker = new Speaker();
                speaker.setBio(speakerJsonObj.getString("bio"));
                speaker.setTitle(speakerJsonObj.getString("title"));
                speaker.setName(speakerJsonObj.getString("name"));
                speaker.setImg(speakerJsonObj.getString("img"));

                session.setSpeaker(speaker);

                sessions.add(session);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sessions;
    }
}
