package checker.ifrs.edu.checker.model.bll;

import java.util.List;

import checker.ifrs.edu.checker.model.dal.QuestaoDal;
import checker.ifrs.edu.checker.vo.Questao;

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
}
