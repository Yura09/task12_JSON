package com.epam.app.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import java.io.File;
import java.io.IOException;

public class Validator {
    public static boolean isValidate(File jsonPath, File schemaPath)
            throws IOException, ProcessingException {
        JsonNode jsonNode = JsonLoader.fromFile(jsonPath);
        JsonNode schemaNode = JsonLoader.fromFile(schemaPath);

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();
        ProcessingReport report = validator.validate(schemaNode, jsonNode);
        return report.isSuccess();
    }
}
