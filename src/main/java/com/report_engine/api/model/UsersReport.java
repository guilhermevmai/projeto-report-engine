package com.report_engine.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.report_engine.api.model.enums.UserStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "nomeUsuario", "codigo", "dataCadastro", "userStatus"})
public class UsersReport {
    private Integer id;
    private String nomeUsuario;
    private Long codigo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;
    private UserStatus userStatus;

    public UsersReport(Integer id, String nomeUsuario, Long codigo,
                       LocalDate dataCadastro, UserStatus userStatus) throws ArrayIndexOutOfBoundsException {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.codigo = codigo;
        this.dataCadastro = dataCadastro;
        this.userStatus = userStatus;
    }
}
