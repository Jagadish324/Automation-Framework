package com.arc.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    public static String getTestIDBySubject(String baseUrl, String chanelId, String accessToken, String subject) {
        ArrayList<String> testIDs = new ArrayList<>();
        RestAssured.baseURI = baseUrl;
        String htmData = RestAssured.given().header("Authorization", "Bearer " + accessToken)
                .queryParam("channel", chanelId)
                .get("conversations.history")
                .getBody()
                .asString();

        try {
            JSONObject json = new JSONObject(htmData);
            JSONArray msgsArray = json.getJSONArray("messages");

            for (int i = 0; i < msgsArray.length(); i++) {
                JSONObject msgObject = msgsArray.getJSONObject(i);
                JSONArray fileArray = msgObject.getJSONArray("files");
                JSONObject fileObject = fileArray.getJSONObject(0);
                String id = fileObject.getString("id");
//                String msgfrom = msgObject.getString("from").toLowerCase();
//                String msgsto = msgObject.getString("to").toLowerCase();
//                from = from.toLowerCase();
                subject = subject.toLowerCase();
                String msgsubject = fileObject.getString("subject").toLowerCase();

//                to = to.toLowerCase();
                if (msgsubject.contains(subject) ) {
                    testIDs.add(id);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        } catch (IndexOutOfBoundsException e) {
//            testIDs.add("No Mail");
//        }

        if (testIDs.size() < 1)
            return "No Mail";
        else
            return testIDs.get(0);
    }

    public static String getMessagesByFilesID(String accessToken, String FilesID) {
        RestAssured.baseURI = "https://slack.com/api/";
//        if(Constants.fileResponse.isEmpty())
        return RestAssured.given().header("Authorization", "Bearer " + accessToken)
//                .queryParam("channel", Constants.channelID)
                .queryParam("file", FilesID)
                .get("files.info")
                .asString();
//        else
//            return Constants.fileResponse;

    }
    public static String getBody(String htmlData) {
//        String htmData = getMessagesByFilesID(testId);
//        System.out.println(htmData);
        JSONObject json = new JSONObject(htmlData);
        JSONObject fileObject = json.getJSONObject("file");
//        boolean h = Boolean.parseBoolean(html);
//        JSONObject firstPart = partsArray.getJSONObject(getB);
        String body = fileObject.getString("plain_text");
//        String bodyText = body.replaceAll("<[^>]*>", "");
        String bodyText = Jsoup.parse(body).text();
        return bodyText;
    }
    public static String[] getLinks(String htmData){
        JSONObject json = new JSONObject(htmData);
        JSONObject fileObject = json.getJSONObject("file");
        String body="";
        try {
            body = fileObject.getString("simplified_html");
        }catch (Exception e){
            body = fileObject.getString("preview");
        }
        // Parse the HTML using Jsoup
        Document document = Jsoup.parse(body);

        // Find all links in the HTML
        Elements links = document.select("a");
        String link[]= new String[links.size()];int t=0;
        // Print the links
        for (Element link1 : links) {
            System.out.println("Link Text: " + link1.text());
            link[t]=link1.attr("href");

            System.out.println("Link URL: " + link[t]);
            System.out.println();
            t++;
        }
        return link;
    }
    public static String getLinkWithKey(String htmData, String key){
        JSONObject json = new JSONObject(htmData);
        JSONObject fileObject = json.getJSONObject("file");
        return fileObject.get(key).toString();
    }
    public static String getFileLink(String token, String attachmentId, String key){
        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .when()
                .get("https://slack.com/api/files.info?file=" + attachmentId);

        String fileUrl = response.jsonPath().getString(key);
        return fileUrl;
    }
    public static void downloadFile(String token, String fileUrl, String fileName){
        Response fileResponse = RestAssured.given()
                .auth()
                .oauth2(token)
                .when()
                .get(fileUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
        byte[] fileContent = fileResponse.asByteArray();

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.write(fileContent);
        } catch (IOException e) {
            // Handle file saving errors
        }
//                .saveContent(filename);
    }
    public static void downloadFile(String token,String fileUrl){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(fileUrl);
        httpGet.addHeader("Authorization", "Bearer " + token);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                // ... (save content to file using inputStream)
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void downloadFiles(String url,String token, String downloadFilePath){
//        String apiUrl = "https://example.com/api/download";
//        String token = "your_token_here";
//        String downloadFilePath = "path/to/downloaded/file.zip";

        // Set the base URI for RestAssured
        RestAssured.baseURI = url;

        // Perform the GET request with the token
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get();

        // Check if the response is successful (status code 200)
        if (response.getStatusCode() == 200) {
            // Save the response body (ZIP file content) to a local file
            saveResponseToFile(response, downloadFilePath);
            System.out.println("File downloaded successfully.");
        } else {
            System.out.println("Failed to download file. HTTP status code: " + response.getStatusCode());
        }
    }
    private static void saveResponseToFile(Response response, String filePath) {
        try (InputStream inputStream = response.getBody().asInputStream();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static String getMessagesByFilesID(String FilesID) {
//        RestAssured.baseURI = "https://slack.com/api/";
////        if(Constants.fileResponse.isEmpty())
//        return Constants.fileResponse=RestAssured.given().header("Authorization", "Bearer " + Constants.ACCESS_TOKEN_SLACK)
////                .queryParam("channel", Constants.channelID)
//                .queryParam("file", FilesID)
//                .get("files.info")
//                .asString();
////        else
////            return Constants.fileResponse;
//
//    }
//    public boolean validateMail(String numberOfTimes, String subject, String timeToWait) {
//        String testId = "";
//        if (subject.contains("survey")) {
//            testId = Constants.TEST_ID_SURVEY;
//        } else if (subject.contains("access code")) {
//            testId = Constants.TEST_ID_ACCESS_CODE;
//        } else {
//            testId=Constants.testID;
////            testId = Constants.testID_MAP.get(to).get(0);
//        }
//        System.out.println("Checking mail for " + subject);
//        System.out.println("Checking mail for " + (Constants.RERUN + 1) + " th time");
//        System.out.println("Existing test id : " + testId);
//
//        try {
//            DynamicWait.waitUntil(Integer.parseInt(timeToWait));
//        }
//        catch (NumberFormatException e){
//            DynamicWait.waitUntil(10);
//        }
////        String check = BasePage.getTestIDBySubjectAndFrom(from, subject, to);
//        String check = BasePage.getTestIDBySubject(subject);
//        System.out.println("Test id we got for last mail : " + check);
//        if (testId.equalsIgnoreCase(check)) {
//            System.out.println("Test id matched as no mail received");
//            if (Constants.RERUN == Integer.parseInt(numberOfTimes)) {
//                return false;
//            } else {
//                Constants.RERUN += 1;
//                return validateMail(numberOfTimes, subject, timeToWait);
//            }
//        } else {
//            if (subject.contains("survey")) {
//                Constants.TEST_ID_SURVEY = check;
//            } else if (subject.contains("access code")) {
//                Constants.TEST_ID_ACCESS_CODE = check;
//            } else {
//                Constants.testID = check;
////                Constants.testID_MAP.get(to).add(0, check);
//            }
////            Constants.testID = check;
//            System.out.println("Mail received");
//
//            Constants.RERUN = 0;
//            return true;
//        }
//    }
//    public static String getTestIDBySubject(String subject) {
//        ArrayList<String> testIDs = new ArrayList<>();
//        RestAssured.baseURI = Constants.BASE_URI_SLACK;
//        String htmData = RestAssured.given().header("Authorization", "Bearer " + Constants.ACCESS_TOKEN_SLACK)
//                .queryParam("channel", Constants.channelID)
//                .get("conversations.history")
//                .getBody()
//                .asString();
//
//        try {
//            JSONObject json = new JSONObject(htmData);
//            JSONArray msgsArray = json.getJSONArray("messages");
//
//            for (int i = 0; i < msgsArray.length(); i++) {
//                JSONObject msgObject = msgsArray.getJSONObject(i);
//                JSONArray fileArray = msgObject.getJSONArray("files");
//                JSONObject fileObject = fileArray.getJSONObject(0);
//                String id = fileObject.getString("id");
////                String msgfrom = msgObject.getString("from").toLowerCase();
////                String msgsto = msgObject.getString("to").toLowerCase();
////                from = from.toLowerCase();
//                subject = subject.toLowerCase();
//                String msgsubject = fileObject.getString("subject").toLowerCase();
//
////                to = to.toLowerCase();
//                if (msgsubject.contains(subject) ) {
//                    testIDs.add(id);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////        } catch (IndexOutOfBoundsException e) {
////            testIDs.add("No Mail");
////        }
//
//        if (testIDs.size() < 1)
//            return "No Mail";
//        else
//            return testIDs.get(0);
//    }
//
//    public static String getBody(String testId) {
//        String htmData = getMessagesByFilesID(testId);
////        System.out.println(htmData);
//        JSONObject json = new JSONObject(htmData);
//        JSONObject fileObject = json.getJSONObject("file");
////        boolean h = Boolean.parseBoolean(html);
////        JSONObject firstPart = partsArray.getJSONObject(getB);
//        String body = fileObject.getString("plain_text");
////        String bodyText = body.replaceAll("<[^>]*>", "");
//        String bodyText = Jsoup.parse(body).text();
//        return bodyText;
//    }
//
//    public static String[] getLinks(String FileID){
//        String htmData = getMessagesByFilesID(FileID);
//        JSONObject json = new JSONObject(htmData);
//        JSONObject fileObject = json.getJSONObject("file");
//        String body = fileObject.getString("preview");
//        // Parse the HTML using Jsoup
//        Document document = Jsoup.parse(body);
//
//        // Find all links in the HTML
//        Elements links = document.select("a");
//        String link[]= new String[links.size()];int t=0;
//        // Print the links
//        for (Element link1 : links) {
//            System.out.println("Link Text: " + link1.text());
//            link[t]=link1.attr("href");
//
//            System.out.println("Link URL: " + link[t]);
//            System.out.println();
//            t++;
//        }
//        return link;
//    }
//
//    public static boolean validateModuleFileName(String name, String to) {
//        System.out.println("Checking if " + name + " is present inside mail body");
//        name = name.trim();
//        boolean status = false;
//        DynamicWait.waitUntil(5);
//        String[] nameArray = name.split(",");
////        String body = BasePage.getBody(Constants.testID_MAP.get(to).get(0), false).toLowerCase();
////        String body = BasePage.getBody(Constants.testID_MAP.get(to).get(0)).toLowerCase();
//        String body = BasePage.getBody(Constants.testID).toLowerCase();
//
////        System.out.println(body);
////        Constants.NEW_CREATED_EQUIPMENT_NAME
//        for (int i = 0; i < nameArray.length; i++) {
//            if (!body.contains(nameArray[i].toLowerCase())) {
//                status = false;
//                break;
//            }
//            if (i == nameArray.length - 1)
//                status = true;
//        }
//        return status;
//    }
//
public static String getInboxDataInStringHTML(String token) {
    RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/";
    return RestAssured.given().header("Authorization", "Bearer " + token)
            .get("inbox")
            .getBody()
            .asString();
}
//    public static ArrayList<String> getTestIDBySubjectAndFrom(String from, String subject, String token) {
//
//        ArrayList<String> testIDs = new ArrayList<>();
////        CONSTANTS.ACCESS_TOKEN="6012da90f7d9492c9283188849642934";
//        RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/";
//        String htmData = RestAssured.given().header("Authorization", "Bearer " + token)
//                .get("inbox")
//                .getBody()
//                .asString();
//        try {
//
//            JSONObject json = new JSONObject(htmData);
//            JSONArray msgsArray = json.getJSONArray("msgs");
//            for (int i = 0; i < msgsArray.length(); i++) {
//                JSONObject msgObject = msgsArray.getJSONObject(i);
//                String id = msgObject.getString("id");
//                if (msgObject.getString("from").equalsIgnoreCase(from) && msgObject.getString("subject").equalsIgnoreCase(subject))
//                    testIDs.add(id);
////                System.out.println("ID: " + id);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return testIDs;
//    }

    public static String getBody(String accessToken, String testId, boolean requiredBody) {
        RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/messages";
        String htmData = RestAssured.given().header("Authorization", "Bearer " + accessToken)
                .get(testId)
                .getBody()
                .asString();
//        System.out.println(htmData);
        JSONObject json = new JSONObject(htmData);
        JSONArray partsArray = json.getJSONArray("parts");
//        boolean h = Boolean.parseBoolean(html);
        int getB = (requiredBody) ? 1 : 0;
        JSONObject firstPart = partsArray.getJSONObject(getB);
        String body = firstPart.getString("body");
        return body;
    }

    public static String getMessagesByTestIDs(String extension, String get, String accessToken) {
//        String token ="6012da90f7d9492c9283188849642934";
        RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/messages/" + extension;
        return RestAssured.given().header("Authorization", "Bearer " + accessToken)
                .get(get)
                .getBody()
                .asString();
    }

    public static String getTestIDBySubjectAndFrom(String from, String subject, String accessToken) {

        ArrayList<String> testIDs = new ArrayList<>();
        try {
            JSONArray msgsArray = getJsonArrayByTag("msgs", accessToken);
            for (int i = 0; i < msgsArray.length(); i++) {
                JSONObject msgObject = msgsArray.getJSONObject(i);
                String id = msgObject.getString("id");
                String msgsubject = msgObject.getString("subject").toLowerCase();
                String msgfrom = msgObject.getString("from").toLowerCase();
                from = from.toLowerCase();
                subject = subject.toLowerCase();
                if (msgfrom.contains(from) && msgsubject.contains(subject)) {
                    testIDs.add(id);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return testIDs.get(0);
    }

    public static JSONArray getJsonArrayByTag(String tag, String accessToken) {
//        Constants.ACCESS_TOKEN="6012da90f7d9492c9283188849642934";
        RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/";
        String htmData = RestAssured.given().header("Authorization", "Bearer " + accessToken)
                .get("inbox")
                .getBody()
                .asString();
        JSONArray msgsArray = null;
        try {
            JSONObject json = new JSONObject(htmData);
            msgsArray = json.getJSONArray(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msgsArray;
    }

    public static String getBody(String testId, boolean requiredBody, String accessToken) {
        RestAssured.baseURI = "https://mailinator.com/api/v2/domains/private/messages";
        String htmData = RestAssured.given().header("Authorization", "Bearer " + accessToken)
                .get(testId)
                .getBody()
                .asString();
//        System.out.println(htmData);
        JSONObject json = new JSONObject(htmData);
        JSONArray partsArray = json.getJSONArray("parts");
//        boolean h = Boolean.parseBoolean(html);
        int getB = (requiredBody) ? 1 : 0;
        JSONObject firstPart = partsArray.getJSONObject(getB);
        String body = firstPart.getString("body");
        return body;
    }

    public static String[] getLinks(String testId, String accessToken) {
        String data = getMessagesByTestIDs(testId, "links", accessToken);
        JSONObject json = new JSONObject(data);
        JSONArray partsArray = json.getJSONArray("links");
        String links[] = new String[partsArray.length()];
        for (int i = 0; i < partsArray.length(); i++) {
            String link = partsArray.getString(i);
            links[i] = link;
            System.out.println("Link " + (i + 1) + ": " + link);
        }
        return links;
    }

    public static String getSlackChat(String authToken, String conversationID) {
        RestAssured.baseURI = "https://slack.com/api/";
        String messageHistory = RestAssured.given().header("Authorization", "Bearer " + authToken)
                .queryParam("channel", conversationID).get("conversations.history").asString();
        return messageHistory;
    }
    public static String extractOTP(String htmlData){
        JSONObject jsonObject = new JSONObject(htmlData);
        JSONObject fileObject = jsonObject.getJSONObject("file");
        String plainText = fileObject.getString("plain_text");
        String otpPattern = "\\d{6}";
        Pattern pattern = Pattern.compile(otpPattern);
        Matcher matcher = pattern.matcher(plainText);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
