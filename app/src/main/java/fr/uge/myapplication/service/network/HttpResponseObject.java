package fr.uge.myapplication.service.network;

public class HttpResponseObject {

    private String statusCode;
    private String data;

    public HttpResponseObject(String statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getData() {
        return data;
    }
}
