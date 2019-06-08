package Compilador;

import java.util.*;
import java.lang.*;
import java.io.*;

import java.lang.reflect.Field;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
    public String sPedro = new String ("Pedro");
    public String sPablo = new String ("Pedro");

    public static void main (String[] args) throws java.lang.Exception, java.lang.IllegalArgumentException, java.lang.IllegalAccessException
    {

     Ideone t = new Ideone();
     for(Field f : t.getClass().getFields()) {

      System.out.println("nombre: " + f.getName());
     }
  }
}