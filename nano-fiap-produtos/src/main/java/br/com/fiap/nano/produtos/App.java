package br.com.fiap.nano.produtos;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;
import br.com.fiap.nano.produtos.repository.CategoriaCollectionRepository;
import br.com.fiap.nano.produtos.repository.ProdutoCollectionRepository;
import br.com.fiap.nano.produtos.view.CategoriaView;
import br.com.fiap.nano.produtos.view.Opcao;
import br.com.fiap.nano.produtos.view.OpcaoView;
import br.com.fiap.nano.produtos.view.ProdutoView;

import javax.swing.*;
import java.util.List;

public class App {
    static void main(String[] args) {
        Opcao opcao = null;

        do {
            opcao = OpcaoView.select();
            switch (opcao) {
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTO -> cadastrarProduto();
                case ALTERAR_PRODUTO -> alterarProduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarProdutoPorId();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarProdutoPorCategoria();
            }
        } while (opcao != Opcao.ENCERRAR_SISTEMA);
    }

    private static void consultarProdutoPorId() {
        Long id = 0l;
        do {
            try {
                id = Long.parseLong(JOptionPane.showInputDialog("Informe o id do produto"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Id inválido!");
            }
        } while (id <= 0);

        Produto p = ProdutoCollectionRepository.findById(id);
        if (p != null) {
            ProdutoView.show(p);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }

    private static void consultarProdutoPorCategoria() {
        Categoria categoria = CategoriaView.select(null);
        List<Produto> produtos = ProdutoCollectionRepository.findByCategoria(categoria);
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não encontramos produtos com a categoria: " + categoria.getNome());
        }else{
            produtos.forEach(ProdutoView::show);
            produtos.forEach(System.out::println);
        }
    }

    private static void cadastrarCategoria() {
        CategoriaView view = new CategoriaView();
        Categoria categoria = view.form(new Categoria());
        CategoriaCollectionRepository.save(categoria);
        view.sucesso(categoria);
    }

    private static void alterarProduto() {
        Produto produto = ProdutoView.select(null);
        ProdutoView.update(produto);
    }

    private static void cadastrarProduto() {
        Produto produto = ProdutoView.form(new Produto());
        ProdutoCollectionRepository.save(produto);
        ProdutoView.sucesso(produto);
    }
}
