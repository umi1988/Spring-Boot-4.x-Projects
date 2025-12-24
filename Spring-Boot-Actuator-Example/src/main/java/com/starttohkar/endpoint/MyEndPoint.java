package com.starttohkar.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "starttohkar")
public class MyEndPoint {

    @ReadOperation
    public MyEndPointResponse features() {
        return new MyEndPointResponse(818181, "Start-Toh-Kar", "Active ");
    }

}


class MyEndPointResponse {
    private int id;
    private String name;
    private String status;

    public MyEndPointResponse() {
    }

    public MyEndPointResponse(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
