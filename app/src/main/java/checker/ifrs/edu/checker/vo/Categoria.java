package checker.ifrs.edu.checker.vo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Categoria extends RealmObject{

    @PrimaryKey
    private int id;
    private String nome;

    public Categoria()
    {

    }

    public Categoria(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

}
