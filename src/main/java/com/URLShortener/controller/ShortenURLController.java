package com.URLShortener.controller;

import com.URLShortener.DTO.MultiCreateShortURLReqDTO;
import com.URLShortener.DTO.ServiceResDTO;
import com.URLShortener.exception.ApplicationException;
import com.URLShortener.model.ShortenedURL;
import com.URLShortener.service.ShortenURLService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ShortenURLController {

    @Autowired
    private ShortenURLService shortenURLService;

    @PostMapping("/shorten")
    public ServiceResDTO createShortUrl(@RequestHeader String originalUrl) throws Exception {
        try {
            ShortenedURL shortenedURL = shortenURLService.shortenURL(originalUrl);
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(true).setResponse(shortenedURL).build();
        } catch (ApplicationException ae) {
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false).setStatusCode(ae.getErrorCode())
                    .setStatusMessage(ae.getMessage()).build();
        } catch (Exception e) {
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false)
                    .setStatusCode("500")
                    .setStatusMessage("unknown error occurred" + e.toString()).build();
        }
    }
    @PostMapping("/shorten-multi")
    public ServiceResDTO createShortUrls(@RequestBody MultiCreateShortURLReqDTO multiCreateShortURLReqDTO) throws Exception {
        try {

//            ArrayList<String> originalUrls = new ArrayList<>();
//            for(long i =0; i< multiCreateShortURLReqDTO.getCount(); i++){
//                String currentTime = String.valueOf(System.nanoTime());
//                String sha256Hash = DigestUtils.sha256Hex(currentTime);
//                shortenURLService.shortenURL("www." + sha256Hash + ".com");
////                originalUrls.add("www." + sha256Hash + ".com");
//            }
            shortenURLService.shortenURLs(multiCreateShortURLReqDTO);
//            for(String originalUrl : multiCreateShortURLReqDTO.getOriginalUrls()){
//                shortenedURLS.add(shortenURLService.shortenURL(originalUrl));
//            }
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(true).setResponse(null).build();
//        } catch (ApplicationException ae) {
//            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false).setStatusCode(ae.getErrorCode())
//                    .setStatusMessage(ae.getMessage()).build();
        } catch (Exception e) {
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false)
                    .setStatusCode("500")
                    .setStatusMessage("unknown error occurred" + e.toString()).build();
        }
    }

    @GetMapping("/get-original-url")
    public ServiceResDTO fetchOriginalUrl(@RequestHeader String shortUrl) throws Exception {
        try {
            ShortenedURL shortenedURL = shortenURLService.getOriginalUrl(shortUrl);
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(true).setResponse(shortenedURL).build();
        } catch (ApplicationException ae) {
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false).setStatusCode(ae.getErrorCode())
                    .setStatusMessage(ae.getMessage()).build();
        } catch (Exception e) {
            return new ServiceResDTO.ServiceResponseBuilder().setStatus(false)
                    .setStatusCode("500")
                    .setStatusMessage("unknown error occurred" + e.getMessage()).build();
        }
    }
}
