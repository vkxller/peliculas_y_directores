package com.diegoreyes.peliculas_y_directores.controladores;

import org.springframework.web.bind.annotation.GetMapping; //Para mapearmetodos HTTP GET
import org.springframework.web.bind.annotation.PathVariable; //Para capturar variables en la URL
import org.springframework.web.bind.annotation.RestController; //Para marcar la clase como controlador REST

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorPeliculas {

    private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();

    //Constructor para inicializar la lista de películas y añadimos peliculas y sus actores
public ControladorPeliculas() {
	listaPeliculas.put("Winnie the Pooh", "Don Hall");	
	listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
	listaPeliculas.put("Tarzán", "Kevin Lima");		
	listaPeliculas.put("Mulán", "Barry Cook");
	listaPeliculas.put("Oliver", "Kevin Lima");	
	listaPeliculas.put("Big Hero 6", "Don Hall");	
}
    //Pagina de inicio
    @GetMapping("/")
    public String holaMundo() {
        return "¡Hola Mundo! Bienvenido a Peliculas y Directores";
    }

    //etodo para obtener tosdas las películas en la ruta "/peliculas"
@GetMapping("/peliculas")
public String obtenerTodasLasPeliculas() {
    if (listaPeliculas.isEmpty()) {
        return "No hay películas en la lista."; //Si la lista está vacia, retornamos este mensaje
    }
    
    //Empezamos con el encabezado
    String resultado = "Lista de todas las películas:\n";
    
    //Recorremos cada película y la vamos agregando al resultado
    for (String pelicula : listaPeliculas.keySet()) {
        resultado = resultado + "- " + pelicula + "\n";
    }
    
    return resultado;
}

    //Mtodo para obtener una película por nombre
    @GetMapping("/peliculas/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        //Buscar la pelicula (ignorando mayusculas/minisculas)
        for (String pelicula : listaPeliculas.keySet()) {
            if (pelicula.equalsIgnoreCase(nombre)) {  //equalsIgnoreCase compara ignorando mayusculas/minusculas
                String director = listaPeliculas.get(pelicula);
                return "Pelicula: " + pelicula + ", Director: " + director;
            }
        }
        return "La pelicula no se encuentra en nuestra lista."; //Si no encuentra la película después de recorrer todo el HashMap
    }

    // Método para obtener películas por director
    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        List<String> peliculasDelDirector = new ArrayList<>();
        
        //Buscar películas del director (ignorando mayusculas/minusculas)
        for (String pelicula : listaPeliculas.keySet()) {
            if (listaPeliculas.get(pelicula).equalsIgnoreCase(nombre)) {
                peliculasDelDirector.add(pelicula);
            }
        }
        
        if (peliculasDelDirector.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista.";
        }
        String resultado = "Películas del director " + nombre + ":\n";
            for (String pelicula : peliculasDelDirector) {
            resultado = resultado + "- " + pelicula + "\n";
        }
        return resultado;
    }
}