package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BikeImageResponseDTO {

    @JsonProperty("bike_image_id")
    private int id;

    private String fileName;

    private String uri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
