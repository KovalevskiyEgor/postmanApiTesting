package models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.Collection;
import java.util.Date;

@Data
public class GetCollectionSuccessResponse {
    @JsonProperty("collection")
    public Collection collection;
    public String id;
    public String name;
    public String owner;
    public Date createdAt;
    public Date updatedAt;
    public String uid;
    public boolean isPublic;
}