package checker.ifrs.edu.checker.model.bll;

import java.util.ArrayList;

import checker.ifrs.edu.checker.model.dal.CategoriaDal;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Categoria;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;
import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class CategoriaBll {

    private CategoriaDal mCategoriaDal;

    /**
     * Metodo construtor
     *
     * @param categoriaDal parametro utilizado para fazer as acoes no banco de dados
     */
    public CategoriaBll(CategoriaDal categoriaDal){
        this.mCategoriaDal = categoriaDal;
    }

    /**
     * Metodo para adiconar uma Categoria ao banco de dados
     *
     * @param categoria Parametro utilizado para armazenar os dados do categoria
     */
    public void addCategoria(Categoria categoria){
        if (categoria == null){
            throw new NullPointerException();
        }

        this.mCategoriaDal.criarCategoria(categoria);
    }

    /**
     * Metodo que busca uma categoria pelo ID
     *
     * @param id Codigo de identificado da categoria
     * @return Categoria Realm
     */
    public Categoria getCategoria(int id){
        Categoria categoria = null;

        try{
            if(isNegativeOrZero(id)){
                throw new InvalidStringException();
            }

            categoria = this.mCategoriaDal.trazerCategoria(id);
        } catch (InvalidStringException e){
            //
        }

        return categoria;
    }

    /**
     * Metodo que busca uma categoria pelo NOME
     *
     * @param nome O nome da categoria
     * @return Categoria Realm
     */
    public Categoria getCategoria(String nome){
        Categoria categoria = null;

        try{
            if(isNullOrEmpty(nome)){
                throw new InvalidStringException();
            }

            categoria = this.mCategoriaDal.trazerCategoria(nome);
        } catch (InvalidStringException e){
            //
        }

        return categoria;
    }

    /**
     * Retorna todas as Categorias do banco de dados
     *
     * @return ArrayList - Lista de categorias
     */
    public ArrayList<Categoria> getAllCategorias(){
        return this.mCategoriaDal.trazerCategorias();
    }
}
