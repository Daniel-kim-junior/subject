package com.ext.subject.service;
import static com.ext.subject.dto.ExtensionDto.*;
import static com.ext.subject.util.common.ExtensionCategory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ext.subject.domain.Extension;
import com.ext.subject.repository.ExtensionRepository;
import com.ext.subject.util.common.ExtensionNotFoundException;
import com.ext.subject.util.common.HttpIpInterceptor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class ExtensionServiceTest {

	@Mock
	private ExtensionCacheService extensionCacheService;

	@Mock
	private ExtensionLogService extensionLogService;

	@Mock
	private HttpIpInterceptor ipInterceptor;

	@Mock
	private ExtensionRepository extensionRepository;

	@InjectMocks
	private ExtensionService extensionService;

	@Autowired
	private ExtensionRepository repository;


	// @Test
	// public void GET_캐시가_있을때_DB와_상호작용_하지_않는_테스트() {
	// 	FixedListCacheData cacheData = mock(FixedListCacheData.class);
	//
	// 	extensionService.readFixedExtensions();
	//
	// 	verifyNoInteractions(extensionRepository);
	// }
	//
	// @Test
	// public void GET_캐시가_없을때_DB와_상호작용_테스트() throws Exception {
	// 	extensionService.readFixedExtensions();
	// 	verify(extensionRepository).findByCategory(FIXED);
	// 	verify(extensionCacheService, times(1)).refreshFixedExtensions(anyList());
	// }
	//
	// @Test
	// public void DELETE_확장자_DB_존재_예외_테스트() throws Exception {
	// 	when(extensionRepository.findByName(anyString()))
	// 		.thenThrow(new ExtensionNotFoundException("삭제 하려는 확장자가 존재하지 않습니다"));
	//
	// 	assertThrows(ExtensionNotFoundException.class, () ->
	// 		extensionService.deleteCustomExtension(new DeleteCustomReqDto(anyString())));
	// 	verify(extensionRepository, times(1)).findByName(anyString());
	// 	verifyNoMoreInteractions(extensionRepository);
	// }
	//
	// @Test
	// public void DELETE_캐시_초기화_및_로그_테스트() throws Exception {
	// 	DeleteCustomReqDto dto = mock(DeleteCustomReqDto.class);
	// 	Extension extension = mock(Extension.class);
	//
	//
	// 	when(extensionRepository.findByName(dto.getExtName())).thenReturn(Optional.of(extension));
	// 	extensionService.deleteCustomExtension(dto);
	//
	//
	// 	verify(extensionRepository, times(1)).findByName(dto.getExtName());
	// 	verify(extensionRepository, times(1)).delete(extension);
	//
	// 	verify(extensionCacheService, times(1)).refreshCustomExtensions(anyList());
	// 	verify(extensionRepository, times(1)).findByCategory(CUSTOM);
	// 	verifyNoMoreInteractions(extensionRepository);
	// 	verify(extensionLogService, times(1)).createExtLog(any());
	// 	verifyNoMoreInteractions(extensionLogService);
	// }


	@Test
	@Rollback(value = false)
	public void POST_확장자_200개_초과_테스트() throws Exception {
		for(int i = 0; i < 200; i++) {
			repository.save(Extension.builder().name(generateRandomString(4)).category(CUSTOM).build());
		}
	}

	private static String generateRandomString(int length) {
		// 사용할 문자들
		String characters = "abcdefghijklmnopqrstuvwxyz";
		Set<String > set = new HashSet<>();
		// 랜덤 문자열 생성
		StringBuilder randomString = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char randomChar = characters.charAt(random.nextInt(characters.length()));
			randomString.append(randomChar);
		}
		if(set.contains(randomString)) {
			return generateRandomString(length);
		}
		set.add(randomString.toString());
		return randomString.toString();
	}

	@Test
	public void PATCH_캐시_초기화_테스트() throws Exception {

	}


}