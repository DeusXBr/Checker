package checker.ifrs.edu.checker.model.dao;

import java.util.ArrayList;
import java.util.List;

import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Questao;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;
import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class CategoriaDao
{

    private Realm mRealm;

    private static final int SUCCESS_OPERATION = 0;
    private static final int FAIL_OPERATION = 1;

    /**
     * Metodo construtor
     *
     */
    public CategoriaDao ()
    {
        this.mRealm = Realm.getDefaultInstance();
    }

    public int criarCategoria(Categoria categoria)
    {
        this.mRealm.beginTransaction();

        try
        {
            this.mRealm.copyToRealm(categoria);
        } catch (IllegalArgumentException | RealmPrimaryKeyConstraintException e)
        {
            this.mRealm.cancelTransaction();
            return FAIL_OPERATION;
        }

        this.mRealm.commitTransaction();

        return SUCCESS_OPERATION;
    }

    public Categoria trazerCategoria(int id)
    {
        Categoria categoria = null;

        if(!isNegativeOrZero(id)){
            categoria = this.mRealm.where(Categoria.class).equalTo("id", id).findFirst();
        }

        return categoria;
    }

    public Categoria trazerCategoria(String nome)
    {
        Categoria categoria = null;

        if (!isNullOrEmpty(nome))
        {
            categoria = this.mRealm.where(Categoria.class).equalTo("nome", nome).findFirst();
        }

        return categoria;
    }

    public RealmResults<Categoria> trazerCategorias()
    {
        return this.mRealm.where(Categoria.class).findAll();
    }


    public List<Questao> trazerQuestoes()
    {
        List<Questao> questaosArrayList = new ArrayList<>();

        RealmResults<Questao> results = this.mRealm.where(Questao.class).findAll();

        if (results.size() != 0)
        {
            for (Questao result: results)
            {
                questaosArrayList.add(result);
            }
        }

        return questaosArrayList;
    }

    public List<Questao> trazerQuestoes(Categoria categoria)
    {
        List<Questao> questaosArrayList = new ArrayList<>();

        RealmResults<Questao> results = this.mRealm.where(Questao.class).equalTo("categoria.id", categoria.getId())
                                                                        .findAll();

        if(results.size() != 0)
        {
            for (Questao result: results)
            {
                questaosArrayList.add(result);
            }
        }

        return questaosArrayList;
    }



    private void resetDatabase()
    {
        if (mRealm != null && mRealm.isInTransaction())
        {
            mRealm.cancelTransaction();
        }
    }

    public void clearDatabase()
    {
        resetDatabase();

        mRealm.beginTransaction();
        mRealm.where(Categoria.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

}
