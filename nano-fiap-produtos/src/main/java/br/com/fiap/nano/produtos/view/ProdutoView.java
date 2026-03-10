package br.com.fiap.nano.produtos.view;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;
import br.com.fiap.nano.produtos.repository.ProdutoCollectionRepository;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoView {

    public static Produto select(Produto produto) {
        Produto ret = (Produto) JOptionPane.showInputDialog(
                null,
                "Selecione um produto",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ProdutoCollectionRepository.findAll().toArray(),
                1);
        return ret;
    }

    public static Produto form(Produto produto) {
        Categoria categoria = null;

        do {
            categoria = CategoriaView.select(produto.getCategoria());
        } while (categoria == null);

        String nome = "";

        do {
            nome = JOptionPane.showInputDialog(null, "Nome do Produto", produto.getNome());

            if (nome.length() < 3) {
                JOptionPane.showMessageDialog(null, "O nome do produto precisa ter no mínimo 3 digitos");
            }
        } while (nome.equals(""));

        String descricao = "";

        do {
            descricao = JOptionPane.showInputDialog(null, "Descrição do Produto", produto.getDescricao());
            if (descricao.length() < 5) {
                JOptionPane.showMessageDialog(null, "A descrição do produto precisa ter no mínimo 5 digitos");
            }
        } while (descricao.equals(""));

        double p = 0;

        do {
            try {
                p = Double.parseDouble(JOptionPane.showInputDialog(null, "Preço do Prouto", produto.getPreco()));
            } catch (NumberFormatException e) {
                p = 0;
            }

            if (p <= 0) JOptionPane.showMessageDialog(null, "Valor inválido");
        } while (p <= 0);

        BigDecimal preco = BigDecimal.valueOf(p);

        Produto ret = produto;
        ret.setNome(nome)
                .setCategoria(categoria)
                .setDescricao(descricao)
                .setPreco(preco)
                .setDataDeCadastro(LocalDateTime.now());

        return ret;
    }

    public static void update(Produto produto) {
        form(produto);
        sucesso(produto);
        show(produto);
    }

    public static void sucesso() {
        JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
    }

    public static void sucesso(Produto produto) {
        JOptionPane.showMessageDialog(null, "Produto " + produto.getNome() + " Salvo com sucesso!");
    }

    public static void show (Produto p) {
        System.out.println(p);
        String produtoString = String.format("PRODUTO: " + p.getNome() + System.lineSeparator() + "DESCRIÇÃO: " + p.getDescricao() + System.lineSeparator() + "CATEGORIA: " + p.getCategoria().toString() + System.lineSeparator() + "PREÇO: %,.2f", p.getPreco());
        JOptionPane.showMessageDialog(null, produtoString);
    }
 }

