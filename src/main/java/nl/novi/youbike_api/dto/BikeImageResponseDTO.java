package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BikeImageResponseDTO {

    @JsonProperty("bike_image_id")
    private int id;

    private String fileNAme;

    private String uri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileNAme() {
        return fileNAme;
    }

    public void setFileNAme(String fileNAme) {
        this.fileNAme = fileNAme;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
