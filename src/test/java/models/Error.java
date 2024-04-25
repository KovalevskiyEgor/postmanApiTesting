package models;

import lombok.Data;

@Data
public class Error{
    public String name;
    public String message;
    public Details details;
}