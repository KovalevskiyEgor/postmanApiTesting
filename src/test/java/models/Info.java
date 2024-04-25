package models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info{
    public String name;
    public String schema;
    @JsonIgnore
    public String id;

    public Info(String name, String schema) {
        this.schema = schema;
        this.name= name;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty("_postman_id")
    public void setId(String id) {
        this.id = id;
    }
}