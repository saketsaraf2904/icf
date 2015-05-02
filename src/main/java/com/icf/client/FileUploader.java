package com.icf.client;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("Usage: Requires the name of a file to upload.");
			System.exit(1);
		}

		RestTemplate template = new RestTemplate();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("userId", "shailajapatole");
		parts.add("password", "shailaja");
		parts.add("domain", "test");
		parts.add("outlookEmailId", "169");
		parts.add("fromUserName", " 	V1111enkiteswaran.P.G <venkiteswaran.puthucode@mahaonline.gov.in>                                       ");
		parts.add("subject", "File Test");
		parts.add("body", "Dear Mr. Nalawade,Request you to kindly share the source code in DOTNET for integration on services. We are planning to integrate these web services on forms related to ‘press services’ .  We would be rolling out this authentic process on the said services by 8th of January.Thanks and Regards,Venkiteswaran.P.G");
		parts.add("from", "Venkiteswaran.P.G <venkiteswaran.puthucode@mahaonline.gov.in>");
		parts.add("to", " 	Dnyaneshwar Nalawade");
		parts.add("cc", "nitin.agarwal@semt.maharashtra.gov.in ;  Amol Ahire <amol.ahire@mahaonline.gov.in> ;  Laxman P. Patil <laxman.patil@mahaonline.gov.in> ;  Niraj Gupta <niraj.gupta@mahaonline.gov.in>");
		parts.add("bcc", "saket");
		parts.add("syncTypeId", "0");
		parts.add("uniqueId", "232");
		parts.add("created", "amit");
		parts.add("mainFolder", "windows1234");
		parts.add("subFolder", "unix1234");
		
		
		//List<MultipartFile> file = null;
		
		//parts.add("files", file);
        //parts.add("files", "null");
        parts.add("files", new FileSystemResource(args[0]));
        //parts.add("files", new FileSystemResource(args[1]));
        parts.add("attachmentCount", "1");

        //parts.add("files", new FileSystemResource(args[2]));
        //parts.add("files", new FileSystemResource(args[3]));
		String response = template.postForObject(
				"http://localhost:8090/kendo/uploadEmail", parts, String.class);
		System.out.println(response);
	}

}