package ru.otus.homeworks;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String statusMessage;
    private final Map<String, String> headers = new LinkedHashMap<>();
    private byte[] body;

    private final Gson gson = new Gson();

    public HttpResponse() {
        this(200, "OK");
    }

    public HttpResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        // стандартные заголовки
        headers.put("Server", "SimpleJavaServer");
        headers.put("Connection", "close");
    }

    public void setStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setBody(byte[] body, String contentType) {
        this.body = body;
        setHeader("Content-Type", contentType);
        setHeader("Content-Length", String.valueOf(body.length));
    }

    public void setJsonBody(Object data) {
        String json = gson.toJson(data);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        setBody(bytes, "application/json; charset=utf-8");
    }

    public void write(OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        writer.write("HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n");

        for (Map.Entry<String, String> header : headers.entrySet()) {
            writer.write(header.getKey() + ": " + header.getValue() + "\r\n");
        }

        writer.write("\r\n");
        writer.flush();

        if (body != null && body.length > 0) {
            outputStream.write(body);
            outputStream.flush();
        }
    }
}


