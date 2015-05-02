package com.icf.client;

import java.io.FileNotFoundException;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FileContacts {

	public static void main(String[] args) throws FileNotFoundException {
		RestTemplate template = new RestTemplate();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("userId", "saketsaraf");
		parts.add("password", "saket");
		parts.add("contactId", 4);
		parts.add("firstName", "saket");
		parts.add("lastName", "saraf");
		parts.add("emailID", "s1.s@gmail.com");
		parts.add("emailID1", "s.s@gmail.com");
		parts.add("phone", "0932030222");
		parts.add("phone1", "0932030222");
		parts.add("telephone", "0932030222");
		parts.add("telephone1", "0932030222");
		parts.add("fax", "0932030222");
		parts.add("fax1", "0932030222");
		parts.add("address1_1", "address");
		parts.add("address1_2", "address");
		parts.add("address1_3", "address");
		parts.add("address1_city", "address");
		parts.add("address1_state", "address");
		parts.add("address1_country", "address");
		parts.add("address2_1", "address");
		parts.add("address2_2", "address");
		parts.add("address2_3", "address");
		parts.add("address2_city", "address");
		parts.add("address2_state", "address");
		parts.add("address2_country", "address");
		parts.add("syncTypeID", 0);

		String response = template.postForObject(
				"http://localhost:8080/kendo/uploadContact", parts,
				String.class);
		System.out.println(response);
	}

}