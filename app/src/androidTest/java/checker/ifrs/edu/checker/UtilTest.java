package checker.ifrs.edu.checker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.utils.ModelUtils;
import checker.ifrs.edu.checker.vo.Avaliacao;
import io.realm.Realm;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UtilTest {

    @Test
    public void autoIncrement() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);

        ModelUtils modelUtils = new ModelUtils();

        AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
        avaliacaoBll.limparBancoDados(); //limpa realm


        int result1 = modelUtils.getProximaIndex(Avaliacao.class); // retorna ultima id vazio
        assertEquals(1, result1);


        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNome("Teste");
        avaliacaoBll.addAvaliacao(avaliacao);

        int result2 = modelUtils.getProximaIndex(Avaliacao.class); // retorna ultima id vazio

        assertEquals(2, result2); // proximo valor disponivel tem que ser 2
    }
}
