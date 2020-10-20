package com.dreamfolkstech.appconfig.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");

    public static final String ENTITY_CREATE_ID_ERROR = "1001";
    public static final String ENTITY_FETCH_MISSING_ID_ERROR = "1002";
	public static final String SUCCESS = "200";
    private ErrorConstants() {
    }
}
