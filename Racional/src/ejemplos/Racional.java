/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racional;

/**
 *
 * @author Ivan Clase que implementa un numero Racional
 */
public class Racional {

    private int numerador;
    private int denominador;

    public Racional() {

    }

    public Racional(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
        simplifica();
    }

    private void simplifica() {
        if (this.numerador > this.denominador) {
            if (this.numerador % this.denominador == 0) {
                this.numerador /= this.denominador;
                this.denominador = 1;
            }
        }else if(this.numerador < this.denominador){
            if (this.denominador % this.numerador == 0){
                this.denominador /= this.numerador;
                this.numerador = 1;
            }
        }else if(this.numerador == this.denominador){
            this.numerador = 1;
            this.denominador = 1;
        }
    }

    public Racional suma(Racional r) {
        int n = (this.numerador * r.getDenominador()) + (this.denominador * r.getNumerador());
        int d = this.denominador * r.getDenominador();

        return new Racional(n, d);
    }

    public Racional opuesto() {
        return new Racional(-this.numerador, this.denominador);
    }

    public Racional resta() {
        return suma(opuesto());
    }

    public Racional multiplica(Racional r) {
        return new Racional(this.numerador + r.getNumerador(), this.denominador + r.getDenominador());
    }

    public Racional inverso() {
        return new Racional(this.denominador, this.numerador);
    }

    public Racional divide(Racional r) {
        return multiplica(r.inverso());
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    @Override
    public String toString() {
        return this.numerador + "/" + this.denominador;
    }

}
