package com.springproject.fileparserwebapp.parsers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParserFactory {
    private final Map<String, Class<? extends Parser>> availableParsers = new HashMap<>();

    public Class<?> addNewParser(String type, Class<? extends Parser> newClass) {
        return availableParsers.put(type, newClass);
    }

    public Parser createParser(MultipartFile file) {
        try {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            Class<?> appropriateClass = availableParsers.get(fileExtension);
            Constructor parserConstructor = appropriateClass.getDeclaredConstructor();
            return (Parser) parserConstructor.newInstance(new Object[] {});
        } catch (Exception e) {
            // TODO: Handle the exception in the right way
            // Right now this is a stub
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
