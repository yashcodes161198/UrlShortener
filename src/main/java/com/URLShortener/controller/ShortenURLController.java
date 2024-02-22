package com.URLShortener.controller;

import com.URLShortener.DTO.ServiceResDTO;
import com.URLShortener.exception.ApplicationException;
import com.URLShortener.model.ShortenedURL;
import com.URLShortener.service.ShortenURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                    .setStatusMessage("unknown error occurred").build();
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
                    .setStatusMessage("unknown error occurred").build();
        }
    }
}
