package models.Response;

import lombok.*;
import models.Error;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCollectionBadResponse {
    public Error error;
}