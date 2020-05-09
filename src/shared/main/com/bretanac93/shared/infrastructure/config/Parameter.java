package com.bretanac93.shared.infrastructure.config;

import com.bretanac93.shared.domain.Service;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class Parameter {
    private final Dotenv dotenv;

    public Parameter(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String get(String key) throws ParameterNotExists {
        String value = dotenv.get(key);

        if (null == value) {
            throw new ParameterNotExists(key);
        }
        return value;
    }

    public Integer getInt(String key) throws ParameterNotExists {
        String value = get(key);

        return Integer.parseInt(value);
    }
}
