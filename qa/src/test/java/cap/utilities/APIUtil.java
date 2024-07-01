package cap.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class APIUtil {
    public static String strCardID;

    public static String strCardURL = "";

    private static Map<String, Set<?>> dataMapForTestCaseDetails = new HashMap<>();

    public static void cardCreation(String strCardName, String desc, String strMemberID,String strLabel, String strImagePath) {

        RestAssured.baseURI = "https://api.trello.com";
        String strURIToken = "/1/cards";

        RequestSpecification request = RestAssured.given();

        request.queryParam("idList", "64590181bc155a7d9abe4ec9");
        // OLD   request.queryParam("idList", "62bc587c4017010bd1b27a36");

        // Key & Token need to be generate from app which is User specific
        request.queryParam("key", "8035cf5b60844c839834c9887318b345");
        request.queryParam("token", "ATTAc27830777f42f86a4e74148a3cc2d4738a650d510a25426bed71215863532af349AEF1EC");


        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("name", strCardName);
        map.put("desc", desc);
        map.put("idMembers", strMemberID);
        map.put("idLabels", strLabel);


        JSONObject requestParams = new JSONObject();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println("key:" + key);
            System.out.println("value:" + map.get(key));
            requestParams.put(key, map.get(key));

        }

        request.header("Content-Type", "application/json"); // Add the Json to the body of the request
        request.body(requestParams.toString());
        Response response = request.post(strURIToken);

        System.out.println("\n Create Trello Card Cover:: Status Code: " + response.getStatusCode());
        System.out.println("---> Response JSON Body: " + response.getBody().asString());

        JSONObject obj = new JSONObject(response.getBody().asString());
        System.out.println("\n >>>> ID = " + obj.get("id"));
        strCardID = obj.get("id").toString();
        strCardURL = obj.get("shortUrl").toString();

        uploadScreenshotInTrelloCard(strCardID, strImagePath);
        removeTrelloCardCover(strCardID);
        addCustomField(strCardID);
        addSourceCustomField(strCardID);
    }

    //    public static void main(String[] args) {
//        System.out.println("--------> ");
//        cardCreation("QA Test Ignore-ePOS","Desc:QA-Testing123","62e76a620681eb2e34f93fd1", "63d0a2337487ca7eac22745a","C:\\Users\\Admin\\OneDrive\\Desktop\\Payzli\\clone\\qa\\config\\files\\ImageJPG.jpg");
//     //   "62e76963e8a5b133dc6caed9", "62bc587c4017010bd1b27a78"
//
//    }
    public static void addSourceCustomField(String strCardID) {
        try {
            RestAssured.baseURI = "https://api.trello.com";
            String strURIToken = "/1/cards/" + strCardID + "/customField/6459338d554d046b7eaa6d02/item";



            RequestSpecification request = RestAssured.given();

            // Key & Token need to be generated which is User specific
            request.queryParam("key", "8035cf5b60844c839834c9887318b345");
            request.queryParam("token", "ATTAc27830777f42f86a4e74148a3cc2d4738a650d510a25426bed71215863532af349AEF1EC");
            request.queryParam("idValue", "645c547ac4e5062a6626716e");

            Response response = request.put(strURIToken);
            System.out.println("\n Add Source Custom field in Trello Card :: Status Code: " + response.getStatusCode());
            System.out.println("---> Response JSON Body: " + response.getBody().asString());


        } catch (Exception ex) {
            System.out.println("WARNING: " + ex.getMessage() + " In API Util class file.");

        }
    }
    public static void uploadScreenshotInTrelloCard(String strCardID, String strScreenshotPath) {

        System.out.println("\n >>Enter into upload Screenshot In Trello Card...");

        try {
            RestAssured.baseURI = "https://api.trello.com";
            String strURIToken = "/1/cards/" + strCardID
                    + "/attachments";

            System.out.println("uri>>>>" + strURIToken);
            RequestSpecification request = RestAssured.given();
            // Key & Token need to be generated which is User specific
            request.queryParam("key", "8035cf5b60844c839834c9887318b345");
            request.queryParam("token", "ATTAc27830777f42f86a4e74148a3cc2d4738a650d510a25426bed71215863532af349AEF1EC");


            request.header("Connection", "keep-alive");
            request.multiPart("file",
                    new File(strScreenshotPath),
                    "image/png");
            Response response = request.post(strURIToken);
            System.out.println("\n API Utils :: Upload Screenshot :: Status Code: "
                    + response.getStatusCode());
            System.out.println("---> Response JSON Body: " + response.getBody().asString());

        } catch (Exception e) {
            System.out.println("\n ---> Exception - Load Testcase details..." +e);
            e.printStackTrace();
        }
    }


    public static void removeTrelloCardCover(String strCardID) {
        try {
            RestAssured.baseURI = "https://api.trello.com";
            String strURIToken = "/1/cards/" + strCardID + "/";

            RequestSpecification request = RestAssured.given();

            // Key & Token need to be generated which is User specific
            request.queryParam("key", "8035cf5b60844c839834c9887318b345");
            request.queryParam("token", "ATTAc27830777f42f86a4e74148a3cc2d4738a650d510a25426bed71215863532af349AEF1EC");

            // create a card under Planned column and Card Name & Description details passing through JSON Body...
            request.body("{\n" +
                    "    \"idAttachmentCover\":\"\"\n" +
                    "}");

            request.header("Content-Type", "application/json");

            Response response = request.put(strURIToken);

            System.out.println("\n remove Trello Card Cover:: Status Code: " + response.getStatusCode());
            System.out.println("---> Response JSON Body: " + response.getBody().asString());
        } catch (Exception ex) {
            System.out.println("WARNING: " + ex.getMessage() + " In API Util class file.");
        }
    }


    public static void addCustomField(String strCardID) {
        try {
            RestAssured.baseURI = "https://api.trello.com";

            String strURIToken = "/1/cards/" + strCardID + "/customField/6459335807470e68951806ff/item";

            String CustomField = System.getProperty("PLATFORM");
//            String CustomField = "Android";



            RequestSpecification request = RestAssured.given();

            // Key & Token need to be generated which is User specific
            request.queryParam("key", "8035cf5b60844c839834c9887318b345");
            request.queryParam("token", "ATTAc27830777f42f86a4e74148a3cc2d4738a650d510a25426bed71215863532af349AEF1EC");
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("text", CustomField);


            JSONObject requestParams = new JSONObject();
            requestParams.put("value", map);


            request.header("Content-Type", "application/json"); // Add the Json to the body of the request
            request.body(requestParams.toString());

            Response response = request.put(strURIToken);

            System.out.println("\n Add Custom field Trello Card Cover:: Status Code: " + response.getStatusCode());
            System.out.println("---> Response JSON Body: " + response.getBody().asString());
        } catch (Exception ex) {
            System.out.println("WARNING: " + ex.getMessage() + " In API Util class file.");
        }
    }



    public static void loadTestcaseData() {
        try {
            String strCycleName=System.getProperty("cycle_name");
            RestAssured.baseURI = "https://apiss.kualitee.com/api/v2";
            String strURIToken = "/test_case_execution/list";
            RequestSpecification request = RestAssured.given();
            //User Token and Project ID...
            if(strCycleName.toLowerCase().contains("android")){
//                request.body("{\"token\" : \"d41b7081bfad27ba099a13cfdeb1aa0d\",\"project_id\" : \"11343\",\"cycle_id\" : \"15919\",\"length\" : \"500\"}");
                request.body("{\"token\" : \"5dcd9b5fb65980b54be1a2f0a181cb7f\",\"project_id\" : \"11343\",\"cycle_id\" : \"15919\",\"length\" : \"500\"}");
            }else if(strCycleName.toLowerCase().contains("ios")){
//                request.body("{\"token\" : \"d41b7081bfad27ba099a13cfdeb1aa0d\",\"project_id\" : \"11343\",\"cycle_id\" : \"15873\",\"length\" : \"500\"}");
                request.body("{\"token\" : \"afc31c07a705b400da3b4c2118fa1f8b\",\"project_id\" : \"11343\",\"cycle_id\" : \"15873\",\"length\" : \"500\"}");
            }
//            if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
//                request.body("{\"token\" : \"d41b7081bfad27ba099a13cfdeb1aa0d\",\"project_id\" : \"11343\",\"cycle_id\" : \"15869\",\"length\" : \"500\"}");
//            } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
//                request.body("{\"token\" : \"d41b7081bfad27ba099a13cfdeb1aa0d\",\"project_id\" : \"11343\",\"cycle_id\" : \"14111\",\"length\" : \"500\"}");
//            }
            request.header("Content-Type", "application/json");
            Response response = request.post(strURIToken);
            System.out.println("\n loadTestcaseData :: Status Code: " + response.getStatusCode());
            String strResponseJSONBody = response.getBody().asString();
            System.out.println("\n" + strResponseJSONBody + "\n");
            JSONObject obj = new JSONObject(strResponseJSONBody);
            JSONArray jsonArray = (JSONArray) obj.get("data");
            System.out.println("\n >>----> TC's ID & Summary: ");
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println("cycle name>>" + strCycleName);
                if (strCycleName.equalsIgnoreCase(jsonArray.getJSONObject(i).get("cycle_name").toString())) {
                    String strTCid = jsonArray.getJSONObject(i).get("tcid").toString();
                    String strSummary = jsonArray.getJSONObject(i).get("summary").toString();
                    String strCycleID = jsonArray.getJSONObject(i).get("cycle_id").toString();
                    String strBuildID = jsonArray.getJSONObject(i).get("build_id").toString();
                    String strScenarioID = jsonArray.getJSONObject(i).get("testscenario_id").toString();
                    Set<String> linkedHashSetTestCasesDetails = new LinkedHashSet<String>();

                    linkedHashSetTestCasesDetails.add(strBuildID);
                    linkedHashSetTestCasesDetails.add(strCycleID);
                    linkedHashSetTestCasesDetails.add(strScenarioID);
                    linkedHashSetTestCasesDetails.add(strTCid);

                    System.out.println("Summary>>" +strSummary);
                    System.out.println("CycleID>>" +strCycleID);
                    System.out.println("BuildID>>" +strBuildID);
                    System.out.println("ScenarioID>>" +strScenarioID);
//                    System.out.println("Summary>>" +strSummary);

                    dataMapForTestCaseDetails.put(strSummary, linkedHashSetTestCasesDetails);
                }
            }
            dataMapForTestCaseDetails.forEach((k, v) -> System.out.println(k + ", " + v));

        } catch (Exception ex) {
            System.out.println("\n--- > Exception for load Testcase Data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Set<String> getTestCaseDetails(String strScenarioOutline) {
        try {
            System.out.println("---> getTestCaseDetails:: "+strScenarioOutline);
            System.out.println("---> setTestcase>>>" + dataMapForTestCaseDetails.get(strScenarioOutline));
            return (Set<String>) dataMapForTestCaseDetails.get(strScenarioOutline);
        } catch (Exception e) {
            System.out.println("--> Warning: Unable to fetch the Testcase details for this scenario: >> "
                    + strScenarioOutline + "\n Exception: " + e.getMessage());
        }
        return null;
    }

    @Test
    public static void updateExecutionStatus(Set<String> setTestcaseDetails, boolean status) {
        String strStatus = "";
        if (status)
            strStatus = "Passed";
        else
            strStatus = "Failed";

        System.out.println("--> setTestcaseDetails : " + setTestcaseDetails);
        try {
            RestAssured.baseURI = "https://apiss.kualitee.com/api/v2";
            String strURIToken = "/test_case_execution/execute";
            RequestSpecification request = RestAssured.given();
//            request.formParam("token", "afc31c07a705b400da3b4c2118fa1f8b");
            request.formParam("token", "8c727679b1777001bc8f1ff8b3bb7fc4");
            request.formParam("project_id", "11343");

            request.formParam("status", strStatus);
            request.formParam("notes", strCardURL);
            request.formParam("cycle_id", setTestcaseDetails.toArray()[1].toString());  //QATest
            request.formParam("build_id", setTestcaseDetails.toArray()[0].toString());  // Build 01
            request.formParam("testscenario_id", setTestcaseDetails.toArray()[2].toString());
            request.formParam("tc_id", setTestcaseDetails.toArray()[3].toString());

            request.formParam("execute", "yes");
            request.formParam("offset_test_executions", "1");

            Response response = request.post(strURIToken);


            System.out.println("-- Get Status for this API's : " + response.getStatusCode());
            String strResponseJSONBody = response.getBody().asString();

//            System.out.println("Return JSON Body: \n" + strResponseJSONBody);
        } catch (Exception ex) {
            System.out.println("Warning: Unable to update the execution status in Kualitee Test Cycle...");
        }
    }
}



