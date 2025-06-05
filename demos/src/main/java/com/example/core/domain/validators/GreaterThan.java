package com.example.core.domain.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GreaterThan.Validator.class)
@Documented
public @interface GreaterThan {
	String message() default "{validation.GreaterThan.message}";

	String minor();

	String major();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class Validator implements ConstraintValidator<GreaterThan, Object> {
		private String minor;
		private String major;

		@Override
		public void initialize(GreaterThan constraintAnnotation) {
			minor = constraintAnnotation.minor();
			major = constraintAnnotation.major();
			ConstraintValidator.super.initialize(constraintAnnotation);
		}

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			if (value == null)
				return false;
			try {
				Field minorFld = value.getClass().getDeclaredField(minor);
				minorFld.setAccessible(true);
				Object minorValue = minorFld.get(value);
				AccessibleObject majorFld = value.getClass().getDeclaredField(major);
				majorFld.setAccessible(true);
				Object majorValue = ((Field) majorFld).get(value);
				if (minorValue == null || majorValue == null || minorValue == majorValue
						|| minorValue.getClass() != majorValue.getClass())
					return false;
				if (minorValue instanceof Comparable)
					return ((Comparable) minorValue).compareTo(majorValue) < 0;
				if (minorValue instanceof Number)
					return ((Number) minorValue).doubleValue() < ((Number) majorValue).doubleValue();
				return minorValue.toString().compareTo(majorValue.toString()) < 0;
			} catch (Exception e) {
				return false;
			}
		}
	}
}
