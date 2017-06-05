package checker.ifrs.edu.checker.utils;

import java.util.Comparator;

import checker.ifrs.edu.checker.vo.Resposta;

/**
 * Created by Fernando Sebenello Soares on 18/05/17.
 */

public class RespostaComparator implements Comparator<Resposta>
{

    /**
     * Metodo que ordena a lista de respostas
     *
     * @param respostaUm uma resposta
     * @param respostaDois a proxima resposta
     * @return
     */
    @Override
    public int compare(Resposta respostaUm, Resposta respostaDois)
    {
        return ((Integer)respostaDois.getQuestao().getId()).compareTo(respostaUm.getQuestao().getId());
    }
}
