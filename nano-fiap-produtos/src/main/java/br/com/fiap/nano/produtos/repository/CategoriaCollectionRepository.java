package br.com.fiap.nano.produtos.repository;

import br.com.fiap.nano.produtos.model.Categoria;
import br.com.fiap.nano.produtos.model.Produto;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CategoriaCollectionRepository {

    private static List<Categoria> categorias;

   static {

       categorias = new Vector<>();
       String[] categorias = {
               "Eletrônicos",
               "Celulares",
               "Livros",
               "Games"};
       Arrays.asList(categorias)
               .forEach(CategoriaCollectionRepository::save);

   }

   public static  Categoria save(String s) {
       Categoria c = new Categoria(s);
       if (!categorias.contains(c)){
           c.setId((long)categorias.size() + 1);
           categorias.add(c);
           return c;
       } else {
           return null;
       }
   }

   public static List<Categoria> findAll(){
       return categorias;
   }

   public static Categoria findById(Long id){
       return categorias.stream()
               .filter(c->c.getId().equals(id))
               .findFirst()
               .orElse(null);
   }


   public static Categoria save(Categoria categoria) {
       if (!categorias.contains(categoria)){
           categoria.setId((long)categorias.size() + 1);
           categorias.add(categoria);
           return categoria;
       }else {
           JOptionPane.showMessageDialog(null, "Já existe uma categoria com o nome informado");
           return null;
       }
   }

}
