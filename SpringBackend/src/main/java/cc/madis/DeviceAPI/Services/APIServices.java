package cc.madis.DeviceAPI.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class APIServices<T, U> extends WebServices<T, U> {
    private RestTemplate restTemplate;
    public HttpHeaders headers;
    public ObjectMapper mapper;

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public APIServices() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.mapper = new ObjectMapper();
    }

    @Override
    public U makeRequest(T value) {
        var entity = new HttpEntity<T>(value, this.headers);
        return (U) restTemplate.postForEntity(
                this.appendedUrl,
                entity, value.getClass());
    }

    @Override
    public U makeRequest(T value, String url) {
        var entity = new HttpEntity<T>(value, this.headers);
        return (U) restTemplate.postForEntity(
                url,
                entity, value.getClass());
    }

    public HashMap parseBody(String body) {
        try {
            return this.mapper.readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>(); 

    }
}
