package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.ParserNotFound;
import org.apache.commons.io.FilenameUtils;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ParserFactory {
    private final Reflections reflections = new Reflections("com.springproject.fileparserwebapp");
    private final HashMap<String, Class<? extends Parser>> availableParsers;

    public ParserFactory() {
        availableParsers = initializeAvailableParsers();
    }

    public Parser getParser(MultipartFile file) {
        try {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            Class<? extends Parser> appropriateClass = getAppropriateClass(fileExtension);
            return createParserFromClass(appropriateClass);
        } catch (Exception e) {
            throw new ParserNotFound(" suitable parser hasn't been found for this file.");
        }
    }

    private Class<? extends Parser> getAppropriateClass(String fileExtension) throws RuntimeException
    {
        for (Map.Entry<String, Class<? extends Parser>> parserClass : availableParsers.entrySet()) {
            if (parserClass.getKey().equals(fileExtension)) {
                return parserClass.getValue();
            }
        }
        throw new RuntimeException();
    }

    private HashMap<String, Class<? extends Parser>> initializeAvailableParsers() {
        HashMap<String, Class<? extends Parser>> parsers = new HashMap<>();
        Set<Class<? extends Parser>> setOfParserClasses = reflections.getSubTypesOf(Parser.class);

        for (Class<? extends Parser> parserClass : setOfParserClasses) {
            String appropriateExtension;
            try {
                appropriateExtension = getAppropriateExtensionFromClass(parserClass);
            } catch (Exception e) {
                throw new ParserNotFound("A error within server.");
            }
            parsers.put(appropriateExtension, parserClass);
        }

        return parsers;
    }

    private String getAppropriateExtensionFromClass(Class<? extends Parser> parserClass) throws Exception {
        return (String) parserClass.getDeclaredMethod("getAppropriateExtension")
                .invoke(createParserFromClass(parserClass));
    }

    private Parser createParserFromClass(Class<? extends Parser> parserClass) throws Exception {
        return parserClass.getConstructor().newInstance();
    }

}
