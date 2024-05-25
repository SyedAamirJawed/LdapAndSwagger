package com.ldap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.ldap.dto.LdapDto;
import com.ldap.service.LdapService;


@Service
public class LdapServiceImpl implements LdapService {

	@Autowired
	private LdapTemplate ldapTemplate;
		private static final String LDAP_BASE_DN="";
	
	
	

	
//------------> Unused service which can't use in Angular APP just for testing <-------------	
	@Override
	public LdapDto getLdapUserById(String userCn) {
		List<LdapDto> ldapAllUser = ldapTemplate.search(LDAP_BASE_DN,"(cn="+userCn+")",
				(AttributesMapper<LdapDto>) attribute ->{
					LdapDto ldapDto = new LdapDto();
					ldapDto.setCn(attribute.get("cn").get().toString());
                    ldapDto.setSn(attribute.get("sn") != null ? attribute.get("sn").get().toString() :"N/A");
                    ldapDto.setMail(attribute.get("mail") != null ? attribute.get("mail").get().toString() : "N/A");  
                    ldapDto.setMobile(attribute.get("mobile") != null ? attribute.get("mobile").get().toString() : "N/A");
					return ldapDto;
				});
		if(ldapAllUser.size()>0) {
			return ldapAllUser.get(0);
		}
		return null;
	}
	
	@Override
	public List<LdapDto> getAllLdap() {
		List<LdapDto> ldapAllUser = ldapTemplate.search(LDAP_BASE_DN,"(objectclass=inetOrgPerson)",
				(AttributesMapper<LdapDto>) attribute ->{
					LdapDto ldapDto = new LdapDto();
					ldapDto.setCn(attribute.get("cn").get().toString());
                    ldapDto.setSn(attribute.get("sn") != null ? attribute.get("sn").get().toString() : "N/A");
                    ldapDto.setMail(attribute.get("mail") != null ? attribute.get("mail").get().toString() : "N/A");  
                    ldapDto.setMobile(attribute.get("mobile") != null ? attribute.get("mobile").get().toString() : "N/A");
					return ldapDto;
				});
		return ldapAllUser;
	}
	
	@Override
	public List<LdapDto> getDynamicLdapUser(String attributeName, String attributeValue ) {
		String ldapFilter = String.format("(&(objectclass=inetOrgPerson)(%s=%s))", attributeName, attributeValue);
		List<LdapDto> ldapAllUser = ldapTemplate.search(LDAP_BASE_DN,ldapFilter,
				(AttributesMapper<LdapDto>) attribute ->{
					LdapDto ldapDto = new LdapDto();
					ldapDto.setCn(attribute.get("cn").get().toString());
                    ldapDto.setSn(attribute.get("sn") != null ? attribute.get("sn").get().toString() : "N/A");
                    ldapDto.setMail(attribute.get("mail") != null ? attribute.get("mail").get().toString() : "N/A");  
                    ldapDto.setMobile(attribute.get("mobile") != null ? attribute.get("mobile").get().toString() : "N/A");
					return ldapDto;
				});
		return ldapAllUser;
	}
}
