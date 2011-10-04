package com.iowacodecamp.entity;

public class SpecialSessionIdentifier {
    public boolean sessionNameRequiresSpecialTreatment(String sessionName) {
        if (sessionName.equals("Breakfast"))
            return true;

        if (sessionName.equals("Welcome and announcements"))
            return true;

        if (sessionName.equals("Break"))
            return true;

        if (sessionName.equals("Lunch"))
            return true;

        if (sessionName.equals("Closing and Prizes"))
            return true;

        return false;
    }
}
