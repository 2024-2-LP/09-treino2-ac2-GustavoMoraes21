package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {

        if(livro == null || livro.getTitulo() == null || livro.getTitulo().isBlank() || livro.getAutor() == null
                || livro.getAutor().isBlank() || livro.getDataPublicacao() == null) {
           throw new ArgumentoInvalidoException("Campos Invalidos");
        }

        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo){

        Boolean livroEncontrado = false;
        if (titulo != null && !titulo.isBlank() && !titulo.isEmpty()) {
            for (int i = 0; i < livros.size(); i++) {
                if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                    livros.remove(i);
                    livroEncontrado = true;
                }
            }
        } else {
            throw new ArgumentoInvalidoException("Campos Invalidos");
        }

        if(!livroEncontrado){
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }

    }

    public Livro buscarLivroPorTitulo(String titulo) {

        if (titulo != null && !titulo.isBlank()) {
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                    return livro;
                }
            }
        }else {
            throw new ArgumentoInvalidoException("Campos Invalidos");
        }
        throw new LivroNaoEncontradoException("Livro não encontrado");
    }

    public Integer contarLivros(){
        return  livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano){
        List<Livro> achados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getDataPublicacao().getYear() <= ano) {
                achados.add(livro);
            }
        }
        return achados;
    }

    public List<Livro> retornarTopCincoLivros(){
        List<Livro> listaOrdenada = new ArrayList<>(livros);

        listaOrdenada.sort((livro1, livro2) -> livro2.calcularMediaAvaliacoes().compareTo(livro1.calcularMediaAvaliacoes()));

        if (listaOrdenada.size() > 5) {
            return listaOrdenada.subList(0, 5);
        } else {
            return listaOrdenada;
        }
    }
}
