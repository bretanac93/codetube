package com.bretanac93.shared.infrastructure.config;


public class ParameterNotExists extends Throwable {
    public ParameterNotExists(String key) {
        super(String.format("The parameter <%s> does not exists in the environment file", key));
    }
}
