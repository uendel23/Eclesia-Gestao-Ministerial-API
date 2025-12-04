package com.eclesia.gestao_ministerial.DTO;

import com.eclesia.gestao_ministerial.enums.StatusMembro;



public class MembroFiltroDto {
    private String cargo;
   private  String ministerio;
    private StatusMembro status;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public StatusMembro getStatus() {
        return status;
    }

    public void setStatus(StatusMembro status) {
        this.status = status;
    }

    public String getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(String ministerio) {
        this.ministerio = ministerio;
    }
}
