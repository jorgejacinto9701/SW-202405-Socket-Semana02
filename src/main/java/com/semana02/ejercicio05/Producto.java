package com.semana02.ejercicio05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;

}
