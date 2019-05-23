package com.azambuja.resource;

import com.azambuja.domain.Categoria;
import com.azambuja.domain.Produto;
import com.azambuja.resource.domain.ProdutoDomainResource;
import com.azambuja.service.CategoriaService;
import com.azambuja.service.ProdutoService;

import org.hibernate.event.spi.PreDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> adicionarSemCategoria(@RequestBody Produto produtoSerSalvo) {
        Produto produto = produtoService.save(produtoSerSalvo);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable("id") Integer id) {
        Produto produto = produtoService.buscaPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping
    public ResponseEntity<?> getAll() {
        List<Produto> produtos = produtoService.getAll();
        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping(path = "/categorias")
    public ResponseEntity<?> adicionarProdutoComCategorias(@RequestBody ProdutoDomainResource produtoDomainResource) {
        Produto produtoASerSalvo = new Produto(null, produtoDomainResource.getNome(), produtoDomainResource.getPreco());
        List<Categoria> categorias = new ArrayList<>();
        for (Integer categoriaId : produtoDomainResource.getCategoriasId()) {
            Categoria categoria = categoriaService.buscaPorId(categoriaId);
            categorias.add(categoria);
        }
        produtoASerSalvo.setCategorias(categorias);
        Produto produto = produtoService.save(produtoASerSalvo);
        if (produto != null) {
            for (Categoria categoria : categorias) {
                categoria.getProdutos().add(produto);
                categoriaService.update(categoria);
            }
        }
        return ResponseEntity.ok().body(produtoASerSalvo);
    }

    @PostMapping(path = "/designarCategorias")
    public ResponseEntity<?> designarCategoriasParaOProduto(@RequestBody ProdutoDomainResource produtoDomainResource) {
        Produto produto = produtoService.buscaPorId(produtoDomainResource.getId());
        List<Categoria> categorias = new ArrayList<>();
        for (Integer categoriaId : produtoDomainResource.getCategoriasId()) {
            categorias.add(categoriaService.buscaPorId(categoriaId));
        }
        produto.setCategorias(categorias);
        produtoService.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarProdutoPorId(@PathVariable("id") Integer id) {
        produtoService.deletarPorId(id);
        Produto produto = produtoService.buscaPorId(id);
        if (produto != null) {
            return ResponseEntity.ok().body("Produto Deletado !");
        } else {
            return ResponseEntity.ok().body("Produto não existe mais na base de dados");
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizarProduto(@RequestBody Produto produtoASerAtualizado) {
        Produto produtoAtualizado = produtoService.update(produtoASerAtualizado);
        return ResponseEntity.ok().body(produtoAtualizado);
    }
}