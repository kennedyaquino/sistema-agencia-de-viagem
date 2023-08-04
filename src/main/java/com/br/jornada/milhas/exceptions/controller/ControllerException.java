package com.br.jornada.milhas.exceptions.controller;

import com.br.jornada.milhas.exceptions.NotFoundException;
import com.br.jornada.milhas.exceptions.models.ErrorMensagem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMensagem> naoEncontrado(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMensagem error = new ErrorMensagem(e.getMessage());
        return ResponseEntity.status(status).body(error);
    }
}
