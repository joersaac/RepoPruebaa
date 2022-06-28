package com.chimichurris.server.controllers;

import com.chimichurris.server.models.dtos.*;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.services.UserService;
import com.chimichurris.server.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenManager tokenManager;
 
	@PostMapping("/signup")
	public ResponseEntity<MessageDTO> registerUser(@RequestBody @Valid UserInfo userInfo, BindingResult result) {
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			
			User foundUser = userService
					.findOneByUsernameAndEmail(userInfo.getUsername(), userInfo.getEmail());
			
			if(foundUser != null) {
				return new ResponseEntity<>(
						new MessageDTO("Este usuario ya existe"),
						HttpStatus.BAD_REQUEST
					);
			}
			
			userService.register(userInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Usuario Registrado"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/login")
	private ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginInfo, BindingResult result) {
		try {
			
			if(result.hasErrors()) {
				return new ResponseEntity<>(
						new TokenDTO(),
						HttpStatus.BAD_REQUEST
					);
			}
			
			User user = userService.findOneByIdentifer(loginInfo.getIdentifier());
			
			if(!userService.comparePassword(user, loginInfo.getPassword())) {
				return new ResponseEntity<>(
						new TokenDTO(),
						HttpStatus.UNAUTHORIZED
					);
			}
			
			final String token = tokenManager.generateJwtToken(user.getUsername());
			
			userService.insertToken(user, token); 
			
			return new ResponseEntity<>(
						new TokenDTO(token, user),
						HttpStatus.OK
					);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(
					new TokenDTO(),
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<VerifyDTO> verifyUserExistance(@RequestBody @Valid UserInfo userInfo, BindingResult result) {
		try {

			if(result.hasErrors()) {
				return new ResponseEntity<>(
						new VerifyDTO("Algo salio mal", false),
						HttpStatus.BAD_REQUEST
				);
			}

			User foundUser = userService
					.findOneByUsernameAndEmail(userInfo.getUsername(), userInfo.getEmail());

			return new ResponseEntity<>(
					new VerifyDTO("", foundUser != null),
					HttpStatus.OK
			);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(
					new VerifyDTO(e.getMessage(), false),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
}
