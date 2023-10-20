package validaciones;

import excepciones.EdadFormatException;
import excepciones.NombreFormatException;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    public static int validarEdad(String text) {
        int edad=-1;
        try {
            int idade = Integer.parseInt(text.trim());
            if(idade<0 | idade>125)throw new EdadFormatException("La edad introducida no es valida recuerde introduzca un numero entre 0 y 125") ;
            else edad=idade;
        } catch (EdadFormatException e){
            JOptionPane.showMessageDialog(null, e);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Edad no Válida: recuerde debe ser valores numericos");
        }
        return edad;
    }

    public static String validarNombre(String text) {
        String nombre="";
        try{
            if(!text.trim().isEmpty() && text.length()<50)nombre=text;
            else throw new NombreFormatException("El nombre no puede estar vacio ni pasar de 50 caracteres");
        }catch (NombreFormatException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return nombre;
    }

    public static boolean emailIsValid(String text) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches())JOptionPane.showMessageDialog(null, "email no válido");
        return matcher.matches();

    }


}
