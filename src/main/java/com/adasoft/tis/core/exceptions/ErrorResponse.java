package com.adasoft.tis.core.exceptions;

import lombok.Builder;

@Builder
public class ErrorResponse implements Problem {

    private final String title;
    private final String message;

    private ErrorResponse(final String title, final String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
