package util;

import io.qameta.allure.Step;
import models.Collection;
import models.Response.*;
import java.util.List;
import static io.restassured.RestAssured.given;

public class Requests {
    public PropertyReader propertyReader = new PropertyReader("config.properties");

    @Step("create collection")
    public SuccessfulResponse createCollection(Collection collection, String uri){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .body("{\"collection\":" + collection.toJsonString() + "}")
                .when()
                .post(uri)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().getObject("collection", SuccessfulResponse.class);
    }
    @Step("get collection")
    public GetCollectionSuccessResponse getCollection(String uri, String id){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .when()
                .get(uri+id)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().getObject("", GetCollectionSuccessResponse.class);
    }
    @Step("try to create collection with bad body")
    public CreateCollectionBadResponse createCollectionBadResponse(Collection collection, String uri){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .body("{\"collection\":" + collection.toJsonString() + "}")
                .when()
                .post(uri)
                .then()
                .assertThat().statusCode(400)
                .extract().body().jsonPath().getObject("collection", CreateCollectionBadResponse.class);
    }
    @Step("update collection")
    public SuccessfulResponse updateCollection(Collection collection, String uri){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .body("{\"collection\":" + collection.toJsonString() + "}")
                .when()
                .put(uri)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().getObject("collection", SuccessfulResponse.class);
    }
    @Step("get collections")
    public List<Collection> getList(String uri){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .when()
                .get(uri)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().getList("collections",Collection.class);
    }
    @Step("delete collection")
    public DeleteCollectionSuccessfulResponse deleteCollection(String uri, String id){
        return given()
                .header("X-API-Key",propertyReader.getProperty("X-API-Key"))
                .when()
                .delete(uri+id)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().getObject("collection",DeleteCollectionSuccessfulResponse.class);
    }
}