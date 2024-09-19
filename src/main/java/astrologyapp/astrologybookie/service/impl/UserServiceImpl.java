package astrologyapp.astrologybookie.service.impl;

import astrologyapp.astrologybookie.model.EditUserDto;
import astrologyapp.astrologybookie.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<EditUserDto> getAllClients() {
        String apiUrl = "http://localhost:8080/api/users";
        ResponseEntity<EditUserDto[]> response =
                restTemplate.getForEntity(apiUrl, EditUserDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
