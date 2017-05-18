package checker.ifrs.edu.checker.utils;

import java.util.Comparator;

import checker.ifrs.edu.checker.vo.Resposta;

/**
 * Created by Fernando Sebenello Soares on 18/05/17.
 */

public class RespostaComparator implements Comparator<Resposta>
{

    @Override
    public int compare(Resposta one, Resposta two)
    {
        return ((Integer)two.getQuestao().getId()).compareTo(one.getQuestao().getId());
    }
}
