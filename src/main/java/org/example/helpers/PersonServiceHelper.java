package org.example.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.constants.Endpoints;
import org.example.model.Person;
import org.example.utils.ConfigManager;

import java.lang.reflect.Type;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class PersonServiceHelper {

    //read config file
    //read url and port from properties file
    //make a get req on this url and send to TestGetPerson

    private static final String BASE_URL= ConfigManager.getInstance().getString("baseUrl");
    private static final String PORT= ConfigManager.getInstance().getString("port");

    public PersonServiceHelper(){
        RestAssured.baseURI=BASE_URL;
        RestAssured.port=Integer.parseInt(PORT);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Person> getAllPerson(){
        Response response=RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(Endpoints.GET_ALL_PERSON)
                .andReturn();

        Type type= new TypeReference<List<Person>>(){}.getType();

        assertEquals(response.getStatusCode(), HttpStatus.SC_OK,"Ok");
        List<Person> personList=response.as(type);
        return personList;

    }

    public Response createPerson(){
        Person person=new Person();
        person.setId("2");
        person.setFirstname("Balaji");
        person.setLastname("R");
        person.setAddress("Melbourne");
        person.setPhoneNumbers("012453623");
        person.setAge(33);

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .when().body(person)
                .post(Endpoints.CREATE_PERSON)
                .andReturn();
        assertEquals(response.statusCode(),HttpStatus.SC_CREATED,"Created");
        return response;
    }
    public Response invalidCreatePerson(){
        Person person=new Person();
        person.setId("2");
        person.setFirstname("Balaji");
        person.setLastname("R");
        person.setAddress("Melbourne");
        person.setPhoneNumbers("012453623");
        person.setAge(33);

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .when().body(person)
                .post(Endpoints.CREATE_PERSON+"1")
                .andReturn();
        assertEquals(response.statusCode(),HttpStatus.SC_NOT_FOUND,"Not found");
        return response;
    }

    public Response updatePerson(Integer id){
        Person person=new Person();
        person.setFirstname("Balaji");
        person.setLastname("Raguram");
        person.setAge(31);
        person.setAddress("Sydney");
        person.setPhoneNumbers("0455602456");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .body(person)
                .patch(Endpoints.UPDATE_PERSON)
                .andReturn();
        assertEquals(response.statusCode(), HttpStatus.SC_OK);
        return response;

    }
public Response testDeletePerson(Integer id){
        Response response=RestAssured.given().contentType(ContentType.JSON)
                .log().all()
                .pathParam("id",id)
                .when()
                .delete(Endpoints.DELETE_PERSON)
                .andReturn();
    assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    return response;
}
}
