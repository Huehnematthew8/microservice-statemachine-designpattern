package com.example.xmlprocessor.controller;

import strategy.XmlProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class XmlProcessorController {

    private final PluginRegistry<XmlProcessingStrategy, String> pluginRegistry;

    @Autowired
    public XmlProcessorController(PluginRegistry<XmlProcessingStrategy, String> pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    @PostMapping("/process")
    public String processXml(@RequestBody String xmlInput, @RequestParam String strategyType) {
        XmlProcessingStrategy strategy = pluginRegistry.getPluginFor(strategyType).orElseThrow(
                () -> new IllegalArgumentException("Invalid strategy type")
        );
        return strategy.process(xmlInput);
    }
}
