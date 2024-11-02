package com.backend.Artview.domain.exhibition.domain;

public enum ExhibitionType {
    UPCOMING("0"),
    ONGOING("1"),
    COMPLETED("2"),
    FREE("3");

    private final String code;

    ExhibitionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
