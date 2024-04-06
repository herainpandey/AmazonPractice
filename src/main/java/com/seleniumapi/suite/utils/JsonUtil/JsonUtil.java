package com.seleniumapi.utils.JsonUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seleniumapi.utils.resourceLoaderUtil.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T  getTestData(String path, Class<T> T) throws IOException {
        try(InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, T);
        }catch (Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }


    public static <T> T  getDeserializeObject(String response, Class<T> T) throws IOException {
            return mapper.readValue(response, T);

    }
}
