package com.truyenvn.core.service;

import com.truyenvn.core.dto.ParameterToQuery;
import com.truyenvn.core.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO userLogin(UserDTO userDTO);
    Object[] findUserByProperties(ParameterToQuery properties);
    UserDTO findById(Integer id);
    UserDTO updateUser(UserDTO dto);
    void insertUser(UserDTO dto);
    Integer updateAvatar(UserDTO dto);
    Integer deleteUser(List<Integer> ids);
}
