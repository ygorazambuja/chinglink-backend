package com.azambuja.resource;

import com.azambuja.domain.Categoria;
import com.azambuja.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/categoria")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Categoria categoriaSerSalva) {
        Categoria categoria = categoriaService.adicionar(categoriaSerSalva);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.buscaPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = categoriaService.getAll();
        return ResponseEntity.ok().body(categorias);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Integer id) {
        categoriaService.deletarPoId(id);
        return ResponseEntity.ok().body("Deletado ! " + id);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Categoria categoriaASerAtualizada) {
        Categoria categoria = categoriaService.update(categoriaASerAtualizada);
        return ResponseEntity.ok().body(categoria);
    }

}