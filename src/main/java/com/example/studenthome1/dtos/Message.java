package com.example.studenthome1.dtos;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Message {

    private String message;

    public Message(String message){
        this.message=message;
    }


}
