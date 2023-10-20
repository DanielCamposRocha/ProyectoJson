package model;

import gui.VentanaBorrarUsuario;
import gui.VentanaCambiarContraseña;
import gui.VentanaCrearUsuario;
import gui.VentanaInicioSesion;
import gui.VentanaMenuUsuario;
import gui.VentanaVerUsuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;

public class AplicacionUsuarios {

	private final String RUTA_FICHERO = "json.txt";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;

	private void crearFicheroJson() {
		File f=new File(RUTA_FICHERO);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("No se ha podido crear el archivo");
			}
		}

	}

	private JSONArray obtenerUsuariosJson() {
		JSONParser parser = new JSONParser();
		Object obj=null;
		try {
			obj = parser.parse(new FileReader(RUTA_FICHERO));
		} catch (IOException | ParseException e) {
			System.out.println("Archivo vacio");;
		}
        JSONArray jsonArray=(JSONArray) obj;
		return jsonArray;
    }

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		if(usuarios!=null){
			for (int i=0;i<usuarios.size();i++){
				JSONObject objectInArray = (JSONObject) usuarios.get(i);
				if(objectInArray.get("Nombre").equals(nombreUsuario)) return i;
			}
		}
		return -1;
    }

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {
		JSONArray jsonArray=obtenerUsuariosJson();
		int posicion=obtenerPosicionUsuario(nombreUsuario,jsonArray);
		JSONObject jsonObject;
		if(posicion==-1) return null;
		else jsonObject=(JSONObject) jsonArray.get(posicion);
        return jsonObject;
    }

	public void ejecutar() {
		crearFicheroJson();
		ventanaInicioSesion=new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
	}

	public void iniciarSesion(String nombreUsuario, String contrasenhaUsuario) {
		JSONObject usuario=obtenerUsuarioJson(nombreUsuario);
		if(usuario!=null) {
			if (usuario.get("Contraseña").equals(contrasenhaUsuario)) {
				ventanaInicioSesion.setTextoUsuario("");
				ventanaInicioSesion.setTextoContraseña("");
				ventanaMenuUsuario = new VentanaMenuUsuario(this, nombreUsuario);
				ventanaMenuUsuario.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
			}
		}else{JOptionPane.showMessageDialog(null, "El usuario no existe");}
	}

	public void cerrarSesion() {
		if(ventanaMenuUsuario!=null)ventanaMenuUsuario.dispose();
		if(ventanaVerUsuario!=null)ventanaVerUsuario.dispose();
		if(ventanaBorrarUsuario!=null)ventanaBorrarUsuario.dispose();
		if(ventanaCambiarContraseña!=null)ventanaCambiarContraseña.dispose();
	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {
		JSONArray arrayUsuarios=null;
		if(obtenerUsuariosJson()!=null)	arrayUsuarios=obtenerUsuariosJson();
		else arrayUsuarios=new JSONArray();
		JSONObject json=new JSONObject();
		if(obtenerPosicionUsuario(nombre,arrayUsuarios)==-1){

				json.put("Nombre", nombre);
				json.put("Contraseña", contraseña);
				json.put("Edad", Integer.parseInt(edad.trim()));
				json.put("Correo", correo);

			arrayUsuarios.add(json);
			escribirJSONArray(arrayUsuarios);
		}else {
			JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");
		}
	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {
		JSONArray jsonArray=obtenerUsuariosJson();
		if(jsonArray!=null){
			for (int i=0;i<jsonArray.size();i++){
				JSONObject objectInArray = (JSONObject) jsonArray.get(i);
				if(objectInArray.get("Nombre").equals(nombreUsuario)){
					objectInArray.replace("Contraseña",nuevaContraseña);
					i= jsonArray.size();
				}
			}
			escribirJSONArray(jsonArray);
		}
		cerrarSesion();
	}

	public void borrarUsuario(String nombreUsuario) {
		JSONArray jsonArray=obtenerUsuariosJson();
		int posicion=obtenerPosicionUsuario(nombreUsuario,jsonArray);
		jsonArray.remove(posicion);
		escribirJSONArray(jsonArray);
		cerrarSesion();
	}

	public void mostrarVentanaCrearUsuario() {
		ventanaCrearUsuario=new VentanaCrearUsuario(this);
		ventanaCrearUsuario.setVisible(true);
	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {
		JSONObject jsonObject=obtenerUsuarioJson(nombreUsuario);
		ventanaVerUsuario=new VentanaVerUsuario(this,nombreUsuario,
				String.valueOf(jsonObject.get("Edad")),String.valueOf(jsonObject.get("Correo")));
		ventanaVerUsuario.setVisible(true);
	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {
		ventanaCambiarContraseña=new VentanaCambiarContraseña(this,nombreUsuario);
		ventanaCambiarContraseña.setVisible(true);

	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		ventanaBorrarUsuario=new VentanaBorrarUsuario(this,nombreUsuario);
		ventanaBorrarUsuario.setVisible(true);

	}

	public void escribirJSONArray(JSONArray jsonArray){
		try (FileWriter fw = new FileWriter(RUTA_FICHERO)){
			jsonArray.writeJSONString(fw);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido escribir en el archivo");
		}
	}
}
