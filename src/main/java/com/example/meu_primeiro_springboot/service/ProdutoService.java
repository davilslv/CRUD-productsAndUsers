package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " +id+ " nao encontrado"));
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        if (!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com ID " +id+ " nao encontrado");
        }

        produtoRepository.deleteById(id);
    }

}
