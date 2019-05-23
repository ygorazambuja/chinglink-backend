package com.azambuja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.azambuja.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}