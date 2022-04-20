package components;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import system.RestAssuredBase;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class components {

    String pointDelete;
    public components(String url) {
        RestAssuredBase.setup(url);
        pointDelete = url;
    }

    public void endpointReturn(){
        get().then().log().all();
    }

    public void validationPath(String path, String value){
        get().then().body(path, is(value));
    }

    public void validationSchema(){
        get().then().body(matchesJsonSchemaInClasspath("jsonValidation.json"));
    }

    public void postEndpoint(){
        JSONObject json = new JSONObject();
        json.put("name", "Teste NB");
        json.put("job", "tester");

        given().body(json).when().post().then().statusCode(HttpStatus.SC_CREATED).body(containsString("createdAt"));
    }

    public void updateEndpoint(){
        JSONObject json = new JSONObject();
        json.put("name", "Teste NB Update");
        json.put("job", "tester update");

        given().body(json).when().put().then().statusCode(HttpStatus.SC_OK).body(containsString("updatedAt"));
    }

    public void deleteEndpoint(){

        when().delete(pointDelete).then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
