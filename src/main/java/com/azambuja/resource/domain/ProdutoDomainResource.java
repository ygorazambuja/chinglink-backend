package com.azambuja.resource.domain;

import com.azambuja.domain.Produto;

import java.util.List;

public class ProdutoDomainResource extends Produto {

    private static final long serialVersionUID = 1L;
    private List<Integer> categoriasId;

    public List<Integer> getCategoriasId() {
        return categoriasId;
    }

    public void setCategoriasId(List<Integer> categoriasId) {
        this.categoriasId = categoriasId;
    }
}
