package nl.novi.youbike_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BikeCommentRequestDTO {

    @NotBlank(message = "Insert a message. (required")
    @Size(max = 255, message = "Comment must be at most 255 characters long.")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
