package br.com.fiap.nano.produtos.view;
import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.repository.CategoriaCollectionRepository;
import javax.swing.*;


public class CategoriaView {
    static CategoriaCollectionRepository repository;

    public static Categoria select(Categoria categoria) {
        Categoria ret = (Categoria) JOptionPane.showInputDialog(
                null,
                "Selecione uma categoria",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                repository.findAll().toArray(),
                categoria == null ? 1 : categoria);
        return ret;

    }

    public void sucesso() {
        JOptionPane.showInputDialog(null, "Categoria foi salva com sucesso!");
    }

    public void sucesso(Categoria categoria) {
        System.out.println(categoria);
        JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " foi salva com sucesso!");
    }

    public static Categoria form(Categoria categoria){
        String nome = JOptionPane.showInputDialog(null, "Informe o nome da categoria", categoria!=null? categoria.getNome() : "");
        return  new Categoria(nome);
    }


}
