package com.epicsoftware.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpecialSessionIdentifierTest {
    private SpecialSessionIdentifier sut;

    @Before
    public void setUp() {
        sut = new SpecialSessionIdentifier();
    }

    @Test
    public void breakfastIsSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Breakfast");
        Assert.assertTrue(isSpecialChar);
    }

    @Test
    public void welcomeIsSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Welcome and announcements");
        Assert.assertTrue(isSpecialChar);
    }

    @Test
    public void breakIsSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Break");
        Assert.assertTrue(isSpecialChar);
    }

    @Test
    public void lunchIsSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Lunch");
        Assert.assertTrue(isSpecialChar);
    }

    @Test
    public void closingIsSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Closing and Prizes");
        Assert.assertTrue(isSpecialChar);
    }

    @Test
    public void normalSessionNameIsNotSpecialSessionName() {
        boolean isSpecialChar = sut.sessionNameRequiresSpecialTreatment("Working with http on the iPhone");
        Assert.assertFalse(isSpecialChar);
    }
}
