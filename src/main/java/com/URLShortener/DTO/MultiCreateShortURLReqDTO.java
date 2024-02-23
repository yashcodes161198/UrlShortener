package com.URLShortener.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MultiCreateShortURLReqDTO {
    ArrayList<String> originalUrls;
    Integer count;

}
