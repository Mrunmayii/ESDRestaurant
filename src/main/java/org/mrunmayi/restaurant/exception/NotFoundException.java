package org.mrunmayi.restaurant.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {
    private final String msg;
}
