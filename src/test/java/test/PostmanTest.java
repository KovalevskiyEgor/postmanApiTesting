package test;

import models.Collection;
import models.Info;
import models.Item;
import models.Request;
import models.Response.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import spec.Specifications;
import util.*;
import java.util.*;

public class PostmanTest {
    public SoftAssert softAssert = new SoftAssert();
    public Requests requests = new Requests();
    public PropertyReader propertyReader = new PropertyReader("config.properties");
    public String URL = propertyReader.getProperty("URI");
    @Test
    public void test(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());

        Request request1 = new Request();
        Item item1 = Item.builder().request(request1).build();
        Info info1 = new Info("My First Postman Collection",propertyReader.getProperty("schema"));
        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(item1);
        Collection collection1 = new Collection(info1, items1);

        SuccessfulResponse createCollectionSuccessfulResponse1 = requests.createCollection(collection1, propertyReader.getProperty("endpoint"));
        softAssert.assertEquals(createCollectionSuccessfulResponse1.getName(),"My First Postman Collection");
        String createdCollectionId = createCollectionSuccessfulResponse1.getId();

        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecError400());
        requests.createCollectionBadResponse(new Collection(), propertyReader.getProperty("endpoint"));

        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
        GetCollectionSuccessResponse collectionById = requests.getCollection(propertyReader.getProperty("endpoint")+"/", createdCollectionId);
        Assert.assertEquals(collectionById.getCollection().getInfo().getId(), createdCollectionId);

        collectionById.getCollection().getInfo().setName("My First Updated Collection");
        SuccessfulResponse updateCollectionSuccessfulResponse = requests.updateCollection(collectionById.getCollection(),propertyReader.getProperty("endpoint")+"/"+createdCollectionId );
        softAssert.assertEquals(updateCollectionSuccessfulResponse.getName(),"My First Updated Collection");

        Request request2 = new Request();
        Item item2 = Item.builder().request(request2).build();
        Info info2 = new Info("mySecondCollection",propertyReader.getProperty("schema"));
        ArrayList<Item> items2 = new ArrayList<>();
        items1.add(item2);
        Collection collection2 = new Collection(info2, items2);
        requests.createCollection(collection2,propertyReader.getProperty("endpoint"));

        Request request3 = new Request();
        Item item3 = Item.builder().request(request3).build();
        Info info3 = new Info("myThirdCollection",propertyReader.getProperty("schema"));
        ArrayList<Item> items3 = new ArrayList<>();
        items1.add(item3);
        Collection collection3 = new Collection(info3, items3);
        requests.createCollection(collection3,propertyReader.getProperty("endpoint"));

        List<Collection> allCollections = requests.getList(propertyReader.getProperty("endpoint"));

        Random random = new Random();
        int rand = random.nextInt(allCollections.size()-1);
        String randId = allCollections.get(rand).getId();

        DeleteCollectionSuccessfulResponse deleteCollectionSuccessfulResponse = requests.deleteCollection(propertyReader.getProperty("endpoint")+"/",randId);
        softAssert.assertEquals(deleteCollectionSuccessfulResponse.getId(),randId,"id is correct");

        softAssert.assertAll();
    }
}