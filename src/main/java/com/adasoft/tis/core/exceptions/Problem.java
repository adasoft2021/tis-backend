package com.adasoft.tis.core.exceptions;

public interface Problem {
    default String getTitle() {
        return null;
    }

    default String getMessage() {
        return null;
    }
}
