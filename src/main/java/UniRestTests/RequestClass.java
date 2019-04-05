package UniRestTests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RequestClass {
    private HttpResponse<JsonNode> jsonResponse;

    public JsonNode sendGetRequest(String requestUrl) throws UnirestException {
        jsonResponse = Unirest.
                get(requestUrl).
                header("accept", "application/json").
                asJson();
        if (jsonResponse.getStatus() == 200) {
            return jsonResponse.getBody();
        }
        return null;
    }

    public int sendPostRequest(String postReqUrl, String reqBody) throws UnirestException {
        jsonResponse = Unirest.
                post(postReqUrl).
                body(reqBody).
                asJson();

        return jsonResponse.getStatus();
    }

    public int sendDeleteRequest(String deleteReqUrl) throws UnirestException {
        jsonResponse = Unirest.
                delete(deleteReqUrl).
                header("accept", "application/json").
                asJson();
        return jsonResponse.getStatus();
    }
}
