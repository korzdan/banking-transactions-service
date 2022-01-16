package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    FILE_UPLOAD("admin:file_upload"),
    EXECUTE_COMMAND("admin:execute_command");

    private final String permission;
}
