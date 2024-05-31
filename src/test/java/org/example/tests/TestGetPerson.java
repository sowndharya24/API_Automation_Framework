package org.example.tests;

import io.cucumber.java.en.Given;
import org.example.helpers.PersonServiceHelper;
import org.example.model.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertNotNull;

public class TestGetPerson {
    private PersonServiceHelper personServiceHelper;
    @BeforeClass
    public void init(){
        personServiceHelper=new PersonServiceHelper();
    }


    @Test
    public void TestGetAllPerson(){
        List<Person> personList=personServiceHelper.getAllPerson();
        Assert.assertNotNull(personList,"Person list is not empty");
        assertFalse(personList.isEmpty(),"Person list is not empty");
    }

}
