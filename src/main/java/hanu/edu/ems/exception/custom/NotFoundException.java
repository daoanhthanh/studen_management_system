package hanu.edu.ems.exception.custom;

import hanu.edu.ems.exception.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends BaseException {
    String attributeName;
    Object value;
    Class<Object> fromClass;
}
