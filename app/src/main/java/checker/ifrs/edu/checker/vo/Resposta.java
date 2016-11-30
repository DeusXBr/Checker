package checker.ifrs.edu.checker.vo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Resposta extends RealmObject {

    @PrimaryKey
    private int id;
    private Avaliacao avaliacao;
    private RealmList<Questao> questoes;
    private RealmList<RealmInt> ints;


}
