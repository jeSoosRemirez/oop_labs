package com.buscomp.parsers;
import com.buscomp.db.entity.*;
import com.buscomp.servlets.util.RequestPack;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;


public class JsonParser {

    public static String toJsonObject(Object entity) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entity);
    }

    public static String toJsonIds(List<String> ids) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ids);
    }

    public static RequestPack parseRequest(String json) throws  JsonProcessingException{
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<RequestPack> reference = new TypeReference<RequestPack>() {};
        return mapper.readValue(json, reference);
    }

    public static User parseUser(String json) throws JsonProcessingException {
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<User> reference = new TypeReference<User>() {};
        return mapper.readValue(json, reference);
    }

    public static Driver parseDriver(String json) throws Exception{
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Driver> reference = new TypeReference<Driver>() {};
        return mapper.readValue(json, reference);
    }

    public static Trip parseTrip(String json) throws Exception{
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Trip> reference = new TypeReference<Trip>() {};
        return mapper.readValue(json, reference);
    }

    public static Vehicle parseVehicle(String json) throws Exception{
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Vehicle> reference = new TypeReference<Vehicle>() {};
        return mapper.readValue(json, reference);
    }
}
