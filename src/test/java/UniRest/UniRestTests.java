package UniRest;

import UniRestTests.RequestClass;
import Utility.JSONReader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

import static Utility.ReportGenerator.*;

public class UniRestTests {
    RequestClass getRequest, postRequest, deleteRequest;

    JSONReader jsonReader;
    private String postReqUrl = "https://reqres.in/api/users";
    private String postReqBody = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    private String deleteReqUrl = "https://reqres.in/api/users/2";


    @BeforeSuite
    public void setUpSuite() {
        setExtent();
    }

    @Test(enabled = false)
    public void shouldReturnStatusOkay() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse =
                Unirest.get("/api/users/2").
                        header("accept", "application/json").
                        asJson();

        Assert.assertEquals(200, jsonResponse.getStatus());
        System.out.println(jsonResponse.getBody());
    }

    @Test
    public void test01_getRequest() throws UnirestException, IOException, ParseException {
        logger = reports.createTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        getRequest = new RequestClass();
        jsonReader = new JSONReader();
        int id_value = getRequest.
                sendGetRequest(jsonReader.ReadJSONFile("get_url", System.getProperty("user.dir") + "/src/main/resources/wsData.json")).
                getObject().
                getJSONObject("data").
                getInt("id");
        Assert.assertEquals(id_value, 2);
    }

    @Test
    public void test02_postRequest() throws UnirestException {
        logger = reports.createTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        postRequest = new RequestClass();
        int status = postRequest.
                sendPostRequest(postReqUrl, postReqBody);
        assert status == 201;
    }

    @Test
    public void test03_deleteRequest() throws UnirestException {
        logger = reports.createTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        deleteRequest = new RequestClass();
        int output = deleteRequest.
                sendDeleteRequest(deleteReqUrl);
        assert output == 204;
    }

    @AfterSuite
    public void tearDownSuite() {
        endReport();
    }
}
