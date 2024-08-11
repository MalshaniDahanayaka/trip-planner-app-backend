package com.uok.tripplanner.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    ERROR_SERIALIZING_LIST(Constants.ERROR_SERIALIZING_LIST),
    LOCATION_SAVING_ERROR(Constants.LOCATION_SAVING_ERROR);

    private final String messageCode;

    public static class Constants {
        public static final String ERROR_SERIALIZING_LIST = "Error serializing selectEventsAndLocationsList";
        public static final String LOCATION_SAVING_ERROR = "Error occurred while saving location";

    }
}

