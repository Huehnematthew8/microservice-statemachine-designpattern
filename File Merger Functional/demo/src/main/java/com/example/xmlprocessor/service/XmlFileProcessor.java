package com.example.xmlprocessor.service;

import org.springframework.stereotype.Service;

@Service
public class XmlFileProcessor {

    private String firstFileContent;
    private boolean firstFileReceived = false;

    public boolean isFirstFileReceived() {
        return firstFileReceived;
    }

    public void receiveFirstFile(String xmlContent) {
        this.firstFileContent = xmlContent;
        this.firstFileReceived = true;
        System.out.println("First XML File Received"); // Log first XML reception
    }

    public String receiveSecondFile(String xmlContent) {
        String secondFileContent = xmlContent;
        System.out.println("Second XML File Received"); // Log second XML reception

        // Simulate merging files and processing
        String mergedXml = mergeFiles(firstFileContent, secondFileContent);

        // Log after processing
        System.out.println("Files Merged and Processed");

        this.firstFileReceived = false; // Reset after processing
        return mergedXml;
    }

    private String mergeFiles(String firstFile, String secondFile) {
        // Simple string concatenation for now
        return firstFile + "\n" + secondFile;
    }
}
