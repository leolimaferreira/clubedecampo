package com.clubedecampo.exception;

import com.clubedecampo.dtos.ErroCampoDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroRespostaDTO handleAreaJaReservadaException(RegistroDuplicadoException e) {
        return ErroRespostaDTO.conflito(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroRespostaDTO handleCampoInvalidoException(CampoInvalidoException e) {
        return new ErroRespostaDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", List.of(new ErroCampoDTO(e.getCampo(), e.getMessage()))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroRespostaDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Erro de validação: {}", e.getMessage());
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampoDTO> listaErros = fieldErrors.stream().map(fe -> new ErroCampoDTO(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroRespostaDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação.", listaErros);
    }

    @ExceptionHandler(AreaJaReservadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroRespostaDTO handleAreaJaReservadaException(AreaJaReservadaException e) {
        return ErroRespostaDTO.conflito(e.getMessage());
    }

    @ExceptionHandler(AreaNaoSuportaNumPessoasException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroRespostaDTO handleAreaNaoSuportaNumPessoasException(AreaNaoSuportaNumPessoasException e) {
        return ErroRespostaDTO.unprocessbleEntity(e.getMessage());
    }

    @ExceptionHandler(AreaNaoReservavelException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroRespostaDTO handleAreaNaoReservavelException(AreaNaoReservavelException e) {
        return ErroRespostaDTO.unprocessbleEntity(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroRespostaDTO handleErrosNaoTratados(RuntimeException e) {
        log.error("Erro inesperado", e);
        return new ErroRespostaDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado. Entre em contato com a administração.", List.of());
    }
}
