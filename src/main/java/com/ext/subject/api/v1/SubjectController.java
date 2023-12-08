package com.ext.subject.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api-v1")
public class SubjectController {

	@GetMapping("/hi")
	public ResponseEntity<?> helloworld() {
		return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
	}
}
