package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    FILE_UPLOAD("file_upload"),
    EXECUTE_COMMAND("execute_command");

    private final String permission;
}
