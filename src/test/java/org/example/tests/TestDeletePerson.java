package org.example.tests;

import org.example.helpers.PersonServiceHelper;
import org.example.model.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class TestDeletePerson {
    private PersonServiceHelper personServiceHelper;
    @BeforeClass
    public void init(){
        personServiceHelper=new PersonServiceHelper();
    }

    @Test
    public void TestDeletePerson(){
        personServiceHelper.testDeletePerson(2);
    }
}
