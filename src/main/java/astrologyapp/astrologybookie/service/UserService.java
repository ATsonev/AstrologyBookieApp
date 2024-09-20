package astrologyapp.astrologybookie.service;

import astrologyapp.astrologybookie.model.dto.EditUserDto;

import java.util.List;

public interface UserService {

    List<EditUserDto> getAllClients();

}
