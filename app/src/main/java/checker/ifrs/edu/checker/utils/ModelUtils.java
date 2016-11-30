package checker.ifrs.edu.checker.utils;

import android.util.Log;

import checker.ifrs.edu.checker.vo.Avaliacao;
import io.realm.Realm;

public class ModelUtils {

    private Realm mRealm;

    public ModelUtils(){
        this.mRealm = Realm.getDefaultInstance();
    }

    /**
     * Este metodo retorna primeiro index livre da tabela requisitada.
     * Implementação do auto increment, já que o realm não suporta.
     *
     * @param classe a classe do object que será procurado no BD
     * @return Um inteiro correspondente ao id livre da tabela
     */
    public int getProximaIndex(Class classe) {
        Number result = this.mRealm.where(classe).count();
        long idVago = 0;

        if(result != null){
            idVago = result.longValue() + 1;
            return (int)idVago;
        }else{
            return 1;
        }
    }

}
