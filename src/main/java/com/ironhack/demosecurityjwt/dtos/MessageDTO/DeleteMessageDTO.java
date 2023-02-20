package com.ironhack.demosecurityjwt.dtos.MessageDTO;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteMessageDTO {
    private String objectType;

    private String objetId;

    public DeleteMessageDTO(String objectType, String objetId) {
        this.objectType = objectType;
        this.objetId = objetId;
    }


}
