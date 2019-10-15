package com.epam.app.parse;

import com.epam.app.constant.Const;
import com.epam.app.model.Gem;
import com.epam.app.validator.Validator;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONParser {
    private final static Logger logger = LogManager.getLogger(JSONParser.class);

    public static void main(String[] args) {
        File file = new File(Const.READ_FROM_FILE);
        File fileSchema = new File(Const.READ_FROM_SCHEMA_FILE);

        try {
            if (Validator.isValidate(file, fileSchema)) {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new FileReader(Const.READ_FROM_FILE));
                Gem[] gem = gson.fromJson(reader, Gem[].class);
                List<Gem> data = Arrays.asList(gem);
                data.forEach(System.out::println);
            } else {
                logger.info("incorrect json file");
            }
        } catch (IOException | ProcessingException e) {
            logger.info(e.getMessage());
        }

    }
}
