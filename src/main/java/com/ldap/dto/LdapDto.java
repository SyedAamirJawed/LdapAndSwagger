package com.ldap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LdapDto {
  
	private String cn;
	private String sn;
	private String mail;
	private String mobile;
	
}
