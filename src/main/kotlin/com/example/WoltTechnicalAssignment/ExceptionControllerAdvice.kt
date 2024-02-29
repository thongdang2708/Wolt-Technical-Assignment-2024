package com.example.WoltTechnicalAssignment

import com.example.WoltTechnicalAssignment.entity.exception.CustomArrayException
import com.example.WoltTechnicalAssignment.entity.exception.CustomException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ExceptionControllerAdvice {


    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentValidationExceptions(
        exception: MethodArgumentNotValidException,
        webRequest: WebRequest
    ): ResponseEntity<Any>? {

        val errors = exception.bindingResult.allErrors.map { fieldError ->
            "${fieldError.defaultMessage}"
        }
        return ResponseEntity(CustomArrayException(errors, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleIllegalStateException(ex: HttpMessageNotReadableException): ResponseEntity<CustomException> {
        val exception = CustomException(ex.message.toString(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity(exception, HttpStatus.BAD_REQUEST)
    }
}