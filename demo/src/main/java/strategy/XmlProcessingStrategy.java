package strategy;

import org.springframework.plugin.core.Plugin;

public interface XmlProcessingStrategy extends Plugin<String> {
    String process(String xmlInput);
}