package model;

import exception.ExceptionsPadrao;

public interface ItemBiblioteca {

    //Interface item biblioteca garante que os métodos devolver e emprestar sejam implementados 
    public void devolver() throws ExceptionsPadrao;
    public void emprestar() throws ExceptionsPadrao;


}
