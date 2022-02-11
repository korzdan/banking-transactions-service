package com.springproject.fileparserwebapp.parsers;

import org.apache.commons.io.FilenameUtils;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
public class ParserFactory {
    private final Reflections reflections = new Reflections("com.springproject.fileparserwebapp");
    private final Set<Class<? extends Parser>> availableParsers = reflections.getSubTypesOf(Parser.class);

    public Parser createParser(MultipartFile file) {
        try {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            for (Class<?> parserClass : availableParsers) {
                if (compareFileExtensionAndClassName(parserClass, fileExtension)) {
                    return (Parser) parserClass.getConstructor().newInstance();
                }
            }
        } catch (Exception e) {
            // TODO: Handle the exception in the right way AFTER merge into master
            // Right now this is a stub
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private boolean compareFileExtensionAndClassName(Class<?> classToCompare, String fileExtension) {
        int lengthOfExtension = fileExtension.length();
        String className = classToCompare.getSimpleName();
        return className.substring(0, lengthOfExtension).equalsIgnoreCase(fileExtension);
    }
}
