package com.epam.app.parse;

import com.epam.app.constant.Const;
import com.epam.app.model.Gem;
import com.epam.app.validator.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JacksonParser {
    private final static Logger logger = LogManager.getLogger(JacksonParser.class);

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(Const.READ_FROM_FILE);
        File fileSchema = new File(Const.READ_FROM_SCHEMA_FILE);
        try {
            if (Validator.isValidate(file, fileSchema)) {
                Gem[] gem = objectMapper.readValue(file, Gem[].class);
                List<Gem> gemList = Arrays.asList(gem);
                gemList.forEach(System.out::println);
            } else {
                logger.info("incorrect json file");

            }
        } catch (ProcessingException | IOException e) {
            logger.info(e.getMessage());
        }
    }
}
