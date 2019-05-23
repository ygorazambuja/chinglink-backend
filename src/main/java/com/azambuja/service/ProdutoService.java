package com.azambuja.service;

import com.azambuja.domain.Categoria;
import com.azambuja.domain.Produto;
import com.azambuja.repository.ProdutoRepository;
import com.azambuja.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto save(Produto produtoSerSalvo) {
        Produto produto = produtoRepository.save(produtoSerSalvo);
        return produto;
    }

    public Produto buscaPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto com o Id: " + id + " da classe " + Categoria.class.getName() + " não foi encontrado."));
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public void deletarPorId(Integer id) {
        Produto produto = buscaPorId(id);
        produtoRepository.delete(produto);
    }

    public Produto update(Produto produtoASerAtualizado) {
        Produto produtoAtualizado = produtoRepository.save(produtoASerAtualizado);
        return produtoRepository.save(produtoASerAtualizado);
    }

}