package com.app.wai.user_service.common.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Base List Response where store any object that will be returned.
 * @param <T> object that will be returned.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseListResponse<T> {
    private boolean success;
    private List<T> datas;
}
