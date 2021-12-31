package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.model.Transaction;

import java.io.File;
import java.util.ArrayList;

public interface Parser {
    ArrayList<Transaction> parse(File file);
}
