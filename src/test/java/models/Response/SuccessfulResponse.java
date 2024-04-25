package models.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuccessfulResponse {
    public String id;
    public String name;
    public String uid;
}