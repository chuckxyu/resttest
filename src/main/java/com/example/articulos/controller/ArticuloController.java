package com.example.articulos.controller;

import java.util.List;

import javax.validation.Valid;

// llamadas de API REST definidas en el controlador

import com.example.articulos.exception.ResourceNotFoundException;
import com.example.articulos.model.Articulo;
import com.example.articulos.repository.ArticuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ArticuloController {

    @Autowired
    ArticuloRepository articuloRepository;


    


    @GetMapping("/articulos")
    public List<Articulo> getAllArticulo() {
        return articuloRepository.findAll();
    }

    @PostMapping("/articulos")
    public Articulo createArticulo(@Valid @RequestBody Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @GetMapping("/articulos/{idarticulo}")
    public Articulo getArticuloById(@PathVariable(value = "idarticulo") String idarticulo) {
        return articuloRepository.findById(idarticulo)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo", "idarticulo", idarticulo));
    }

    @PutMapping("/articulos/{idarticulo}")
    public Articulo updateArticulo(@PathVariable(value = "idarticulo") String idarticulo,
                                           @Valid @RequestBody Articulo articuloDetaille) {

        Articulo articulo = articuloRepository.findById(idarticulo)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo", "idarticulo", idarticulo));

        articulo.setDescripcion(articuloDetaille.getDescripcion());
        articulo.setPrecio(articuloDetaille.getPrecio());
        articulo.setModelo(articuloDetaille.getModelo());

        Articulo articuloAct = articuloRepository.save(articulo);
        return articuloAct;
    }

    @DeleteMapping("/articulos/{idarticulo}")
    public ResponseEntity<?> deleteArticulo(@PathVariable(value = "idarticulo") String idarticulo) {
        Articulo articulo = articuloRepository.findById(idarticulo)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo", "idarticulo", idarticulo));

        articuloRepository.delete(articulo);

        return ResponseEntity.ok().build();
    }
}
