package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;

import java.io.InputStream;
import java.util.ArrayList;

public interface Parser {
    ArrayList<Transaction> parse(InputStream inputStream, User currentUser)
            throws InvalidFileException, FileParserException;
    String getAppropriateExtension();
}
