package checker.ifrs.edu.checker.vo;

import io.realm.RealmObject;

public class RealmInt extends RealmObject{

    private int valor;

    public RealmInt(){

    }

    public RealmInt(int valor){
        this.valor = valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }
}
