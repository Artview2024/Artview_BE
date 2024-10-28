package com.backend.Artview.domain.exhibition.domain;

public enum ProgressType {
    UPCOMING("0"),
    ONGOING("1"),
    COMPLETED("2");

    private final String code;

    ProgressType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
