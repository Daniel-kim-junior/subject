package com.ext.subject.api.v1;

import static com.ext.subject.dto.ExtensionDto.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.ext.subject.service.ExtensionService;

@SpringBootTest
@Transactional
public class CreateFixedExtClass {

	@Autowired
	private ExtensionService extensionService;


	@Test
	@Rollback(value = false)
	public void create() {
		List<PostFixedReqDto> list = new ArrayList<>();
		String nameList[] = {"bat", "cmd", "com", "cpl", "exe", "scr", "js"};
		for(int i = 0; i < 7; i++) {
			list.add(new PostFixedReqDto(nameList[i]));
		}

		extensionService.createInitFixedList(list);
	}

}
