package com.gyp.jedis.util;

import com.gyp.jedis.exception.SystemExceptionHandler;
import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
public class ValidatorUtil {
    public static final void validator(Object in) {
        Set<ConstraintViolation<Object>> validate = getInstance().validate(in);

        if (CollectionUtils.isEmpty(validate)) {
            SystemExceptionHandler.throwException(new ConstraintViolationException(validate));
        }
    }

    public static final Validator getInstance() {
        return ValidatorHolder.INSTANCE;
    }

    private static class ValidatorHolder {
        private static final Validator INSTANCE;

        static {
            ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                    .configure()
                    .addProperty("hibernate.validator.fail_fast", "true")
                    .buildValidatorFactory();
            INSTANCE = validatorFactory.getValidator();
        }
    }

}
