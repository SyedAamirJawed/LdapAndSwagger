package com.ldap.service;

import java.util.List;

import com.ldap.dto.LdapDto;


public interface LdapService {
  LdapDto getLdapUserById(String cn);
  List<LdapDto> getAllLdap();
  List<LdapDto> getDynamicLdapUser(String attributeName,String attributeValue);

}
