package org.example.tests;

import org.example.helpers.PersonServiceHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPatchPerson {
    private PersonServiceHelper personServiceHelper;
    @BeforeClass
    public void init(){
        personServiceHelper=new PersonServiceHelper();
    }


    @Test
    public void testPatchPerson(){
        String id=personServiceHelper.updatePerson(2).jsonPath().getString("id");
        System.out.println("id = " + id);
        Assert.assertNotNull(id,"id not empty");
        Assert.assertEquals(id,"2");
    }

}
