package com.example.pereira.enus;

public enum Status {
    CRIADO,
    CONFIRMADO,
    CONFIRMADO_SEM_INTEGRACAO,
    CANCELADO;
    public static Status devolveStatus(Integer status){
        return switch (status){
          case 0 -> CRIADO;
          case 1 -> CONFIRMADO;
          case 2 -> CONFIRMADO_SEM_INTEGRACAO;
          case 3 -> CANCELADO;
            default -> throw new RuntimeException("status invalido");
        };
    }

}
