package com.azambuja.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "seq_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
	@Column(name = "PR_ID")
	private Integer id;
	@Column(name = "PR_NOME")
	private String nome;

	@Column(name = "PR_PRECO")
	private Double preco;

	@ManyToMany
	@JoinTable(name = "TB_PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "PR_ID"), inverseJoinColumns = @JoinColumn(name = "CA_ID"))
	private List<Categoria> categorias = new ArrayList<>();

	public Produto() {

	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Produto produto = (Produto) o;
		return id.equals(produto.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
