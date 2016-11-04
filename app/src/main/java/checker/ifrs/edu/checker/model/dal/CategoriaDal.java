package checker.ifrs.edu.checker.model.dal;


import java.util.ArrayList;

import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;
import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class CategoriaDal {

    private Realm mRealm;

    private static final int SUCCESS_OPERATION = 0;
    private static final int FAIL_OPERATION = 1;

    public CategoriaDal(){
        this.mRealm = Realm.getDefaultInstance();
    }

    public int criarCategoria(Categoria categoria){
        this.mRealm.beginTransaction();

        try {
            this.mRealm.copyToRealm(categoria);
        } catch (IllegalArgumentException | RealmPrimaryKeyConstraintException e){
            this.mRealm.cancelTransaction();
            return FAIL_OPERATION;
        }

        this.mRealm.commitTransaction();
        this.closeRealm();

        return SUCCESS_OPERATION;
    }

    public Categoria trazerCategoria(int id){
        Categoria categoria = null;

        if(!isNegativeOrZero(id)){
            categoria = this.mRealm.where(Categoria.class).equalTo("id", id).findFirst();
        }

        return categoria;
    }

    public Categoria trazerCategoria(String nome){
        Categoria categoria = null;

        if(!isNullOrEmpty(nome)){
            categoria = this.mRealm.where(Categoria.class).equalTo("nome", nome).findFirst();
        }

        return categoria;
    }

    public ArrayList<Categoria> trazerCategorias(){
        ArrayList<Categoria> categoriaArrayList = new ArrayList<>();

        RealmResults<Categoria> results = this.mRealm.where(Categoria.class).findAll();

        if(results.size() != 0){
            for (Categoria result: results) {
                categoriaArrayList.add(result);
            }
        }

        return categoriaArrayList;
    }

    private void resetDatabase(){
        if(mRealm != null && mRealm.isInTransaction()){
            mRealm.cancelTransaction();
        }
    }

    public void clearDatabase(){
        resetDatabase();

        mRealm.beginTransaction();
        mRealm.where(Categoria.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();

        this.closeRealm();
    }

    public void closeRealm(){
        mRealm.close();
    }

}
