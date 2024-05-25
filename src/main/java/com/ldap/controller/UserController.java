package com.ldap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldap.dto.LdapDto;
import com.ldap.dto.LdapSearch;
import com.ldap.service.LdapService;

@RestController
public class UserController {

	@Autowired
	private LdapService ldapService;

	@GetMapping("/")
	public String welCome() {
		return "Welcome to the home page!";
	}

	@GetMapping("/ldap-all")
	public ResponseEntity<List<LdapDto>> getAllLdapUser() {
		List<LdapDto> allLdap = ldapService.getAllLdap();
		return new ResponseEntity<List<LdapDto>>(allLdap, HttpStatus.OK);
	}
	

	@PostMapping("/dynamic")
	public ResponseEntity<List<LdapDto>> getDynamicLdap(@RequestBody LdapSearch ldapSerach) {
		List<LdapDto> ldapUser = ldapService.getDynamicLdapUser(ldapSerach.getAttributeName(),
				ldapSerach.getAttributeValue());
		return new ResponseEntity<List<LdapDto>>(ldapUser, HttpStatus.OK);
	}

	@GetMapping("/ldap")
	public ResponseEntity<LdapDto> getLdapUserByUid(@RequestParam("username") String username) {
		LdapDto ldapUserById = ldapService.getLdapUserById(username);
		return new ResponseEntity<>(ldapUserById, HttpStatus.OK);
	}

	
}
