package checker.ifrs.edu.checker.model.bll;

import java.util.List;

import checker.ifrs.edu.checker.model.dal.QuestaoDal;
import checker.ifrs.edu.checker.utils.exception.InvalidStringException;
import checker.ifrs.edu.checker.vo.Questao;

import static checker.ifrs.edu.checker.utils.StringUtils.isNegativeOrZero;
import static checker.ifrs.edu.checker.utils.StringUtils.isNullOrEmpty;

public class QuestaoBll {

    private QuestaoDal mQuestaoDal;

    /**
     * Metodo construtor
     *
     */
    public QuestaoBll(){
        mQuestaoDal = new QuestaoDal();
    }

    /**
     * Retorna todas as Questões de uma categoria
     *
     * @return List - Lista de questoes
     */
    public List<Questao> getAllQuestoesByCategoria(String nomeCategoria){
        return this.mQuestaoDal.trazerQuestoes(nomeCategoria);
    }

    /**
     * Metodo que busca uma questao pelo ID
     *
     * @param id Codigo de identificado da questao
     * @return Questao Realm
     */
    public Questao getQuestao(int id){
        Questao questao = null;

        try{
            if(isNegativeOrZero(id)){
                throw new InvalidStringException();
            }

            questao = this.mQuestaoDal.trazerQuestao(id);
        } catch (InvalidStringException e){
            //
        }

        return questao;
    }
}
