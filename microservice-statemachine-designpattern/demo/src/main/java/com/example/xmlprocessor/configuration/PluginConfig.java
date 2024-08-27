package com.example.xmlprocessor.configuration;

import strategy.XmlProcessingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PluginConfig {

    @Bean
    public PluginRegistry<XmlProcessingStrategy, String> pluginRegistry() {
        List<XmlProcessingStrategy> strategies = loadExternalStrategies();
        return SimplePluginRegistry.of(strategies);
    }

    private List<XmlProcessingStrategy> loadExternalStrategies() {
        List<XmlProcessingStrategy> strategies = new ArrayList<>();
        try {
            File strategiesDir = new File("strategies");
            if (strategiesDir.exists() && strategiesDir.isDirectory()) {
                URL[] urls = {strategiesDir.toURI().toURL()};
                URLClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());

                // Load classes based on their fully qualified names
                for (File file : strategiesDir.listFiles()) {
                    if (file.isDirectory()) {
                        loadClassesFromDirectory(file, strategiesDir.getAbsolutePath(), loader, strategies);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strategies;
    }

    private void loadClassesFromDirectory(File directory, String basePath, URLClassLoader loader, List<XmlProcessingStrategy> strategies) {
        try {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    loadClassesFromDirectory(file, basePath, loader, strategies);
                } else if (file.getName().endsWith(".class")) {
                    String className = file.getAbsolutePath()
                            .replace(basePath + File.separator, "")
                            .replace(File.separator, ".")
                            .replace(".class", "");

                    Class<?> clazz = loader.loadClass(className);
                    if (XmlProcessingStrategy.class.isAssignableFrom(clazz)) {
                        XmlProcessingStrategy strategy = (XmlProcessingStrategy) clazz.getDeclaredConstructor().newInstance();
                        strategies.add(strategy);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
