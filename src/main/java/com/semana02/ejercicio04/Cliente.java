package com.semana02.ejercicio04;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;

}
