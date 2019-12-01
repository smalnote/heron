package com.github.smalnote.heron.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true, builderClassName = "UserInternalBuilder", builderMethodName = "internalBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    private BigDecimal blance;

    private Long postNo;

    public static Builder builder(boolean initWithZeroValues) {
        return new Builder(initWithZeroValues);
    }

    public static Builder builder() {
        return new Builder(false);
    }

    public static class Builder extends UserInternalBuilder {
        boolean initWithZeroValues;

        Builder(boolean initWithZeroValues) {
            super();
            this.initWithZeroValues = initWithZeroValues;
        }

        public User build() {
            User user = super.build();
            if (this.initWithZeroValues) {
                try {
                    Field[] declaredFields = user.getClass().getDeclaredFields();
                    for (Field field : declaredFields) {
                        field.setAccessible(true);
                        if (field.get(user) != null) {
                            continue;
                        }
                        Object defaultValue;
                        if (field.getType().isAssignableFrom(String.class)) {
                            defaultValue = "";
                        } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                            defaultValue = BigDecimal.ZERO;
                        } else if (field.getType().isAssignableFrom(Integer.class)) {
                            defaultValue = new Integer(0);
                        } else if (field.getType().isAssignableFrom(Long.class)) {
                            defaultValue = new Long(0L);
                        } else if (field.getType().isAssignableFrom(Short.class)) {
                            defaultValue = new Short((short) 0);
                        } else if (field.getType().isAssignableFrom(Character.class)) {
                            defaultValue = new Character(' ');
                        } else if (field.getType().isAssignableFrom(Byte.class)) {
                            defaultValue = new Byte((byte) ' ');
                        } else {
                            throw new RuntimeException("unsupported zero-value initialize type " + field.getType().getName());
                        }
                        field.set(user, defaultValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            return user;
        }
    }

}
