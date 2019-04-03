package com.spoom.gear.config;

import com.spoom.gear.utils.BaseResult;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * package com.spoom.gear.config
 *
 * @author spoomlan
 * @date 2019-04-03
 */
@Slf4j
@RestControllerAdvice
public class GearExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    StringBuilder errorMsg = new StringBuilder();
    List<FieldError> list = e.getBindingResult().getFieldErrors();
    for (FieldError error : list) {
      log.info("error: " + error.getDefaultMessage());
      errorMsg.append(error.getDefaultMessage()).append(".");
    }
    return BaseResult.error(501, errorMsg.toString());
  }
}
