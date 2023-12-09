package com.ext.subject.util.common;

import static com.ext.subject.dto.ExtensionDto.*;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidExceed200EaValidator implements ConstraintValidator<Exceed200EaCustomExt, List<GetCustomResDto>> {

	@Override
	public boolean isValid(final List<GetCustomResDto> value, final ConstraintValidatorContext context) {
		return value.size() < 200;
	}
}
