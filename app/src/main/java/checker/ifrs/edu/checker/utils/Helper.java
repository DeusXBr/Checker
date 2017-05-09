package checker.ifrs.edu.checker.utils;

import android.content.Context;
import android.content.SharedPreferences;

import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.view.activities.MainActivity;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class Helper
{
    /**
     * Método que pega a avaliação no SharedPreferences
     * @param context
     * @return Avaliacao Object
     */
    public static Avaliacao getAvaliacao(Context context)
    {
        SharedPreferences sharedPrefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        final String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);

        AvaliacaoBll avaliacaoBll = new AvaliacaoBll();

        return avaliacaoBll.getAvaliacao(avaliacaoTitulo);
    }

}
