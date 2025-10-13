package com.app.wai.user_service.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Base Response class that return only one value.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseValueResponse<T> {
    private T data;
}
