package com.projeto.jwt.enums;

public enum Status {

     PENDENTE("PENDENTE"),
     CONCLUIDA("CONCLUIDA"),
     CANCELADA("CANCELADA");


    private String status;

    Status(String status){
        this.status =status;
    }

    public String getStatus() {
        return status;
    }
}
