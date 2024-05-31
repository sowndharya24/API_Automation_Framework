package org.example.tests;

import io.cucumber.java.en.Then;
import org.example.helpers.PersonServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestPostPerson {
    private PersonServiceHelper personServiceHelper;
    @BeforeClass
    public void init(){
        personServiceHelper=new PersonServiceHelper();
    }

    @Test

    public void testPostCreatePerson(){
        String id=personServiceHelper.createPerson().jsonPath().getString("id");
        System.out.println("id = " + id);
        assertNotNull(id,"Person id is not empty");
        assertFalse(id.isEmpty(),"Person id is not empty");

    }
    @Test
    public void testPostInvalidCreatePerson(){
        Integer invalidStatusCode=personServiceHelper.invalidCreatePerson().getStatusCode();
        System.out.println("Status code = " + invalidStatusCode);
        assertNotNull(invalidStatusCode,"Status code is not empty");


    }
}
