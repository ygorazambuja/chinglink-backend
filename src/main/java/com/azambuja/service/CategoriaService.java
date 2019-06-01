package com.azambuja.service;

import java.util.List;
import java.util.Optional;

import com.azambuja.domain.Categoria;
import com.azambuja.repository.CategoriaRepository;

import com.azambuja.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria adicionar(Categoria categoriaSerSalva) {
        categoriaSerSalva.setId(null);
        return categoriaRepository.save(categoriaSerSalva);

    }

    public Categoria buscaPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto com o Id: " + id + " da classe " + Categoria.class.getName() + " não foi encontrado."));
    }

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public void deletarPoId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (!categoria.get().getProdutos().isEmpty())
            throw new RuntimeException("Não posso fazer isso fera, existem produtos dentro dessa categoria");
        categoriaRepository.delete(categoria.orElseThrow(() -> new ObjectNotFoundException("Deu pau")));
    }

    public Categoria update(Categoria categoriaSerAtualizada) {
        Categoria categoriaAtualizada = categoriaRepository.save(categoriaSerAtualizada);
//        return categoriaRepository.save(categoriaAtualizada);
        return categoriaAtualizada;
    }

}