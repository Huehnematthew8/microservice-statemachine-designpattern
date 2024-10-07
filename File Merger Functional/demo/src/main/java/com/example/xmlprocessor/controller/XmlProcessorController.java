package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.service.XmlFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xml")
public class XmlProcessorController {

    @Autowired
    private XmlFileProcessor xmlFileProcessor;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody String xmlContent) {
        try {
            // Check if this is the first or second file based on internal state
            if (!xmlFileProcessor.isFirstFileReceived()) {
                xmlFileProcessor.receiveFirstFile(xmlContent); // First file upload
                return new ResponseEntity<>("First XML File Received", HttpStatus.OK);
            } else {
                String mergedXml = xmlFileProcessor.receiveSecondFile(xmlContent); // Second file upload
                return new ResponseEntity<>("Second XML File Received and Merged\nMerged XML:\n" + mergedXml, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage()); // Log any errors
            return new ResponseEntity<>("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
