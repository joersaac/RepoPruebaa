package com.chimichurris.server.services;

import com.chimichurris.server.models.dtos.UserInfo;
import com.chimichurris.server.models.dtos.UserListByRolDTO;
import com.chimichurris.server.models.entities.User;

import java.util.List;

public interface UserService {
	void register(UserInfo userInfo) throws Exception;
	User findOneById(Long id) throws Exception;
	List<User> findAll() throws Exception;
	User findOneByIdentifer(String identifier) throws Exception;
	User findOneByUsernameAndEmail(String username, String email) throws Exception;
	Boolean comparePassword(User user, String passToCompare) throws Exception;
	void insertToken(User user, String token) throws Exception;
	Boolean isTokenValid(User user, String token) throws Exception;
	User getUserAuthenticated() throws Exception;
	List<UserListByRolDTO> findAllByRol(String rol);
}
