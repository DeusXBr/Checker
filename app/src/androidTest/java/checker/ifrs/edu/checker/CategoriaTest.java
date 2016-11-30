package checker.ifrs.edu.checker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.model.dal.CategoriaDal;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.Realm;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CategoriaTest {

    @Test
    public void addCategoria() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);

        CategoriaBll categoriaBll = new CategoriaBll();

        // limpa database antes do teste
        categoriaBll.limparBancoDados();

        Categoria categoria = new Categoria(1, "categoria1");
        Categoria categoria2 = new Categoria(1, "categoria2");

        categoriaBll.addCategoria(categoria);
        categoriaBll.addCategoria(categoria2);

        assertEquals(1, categoriaBll.getAllCategorias().size());
        assertEquals("categoria1", categoriaBll.getCategoria(1).getNome());
    }

    @Test
    public void getAllCategorias() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);

        CategoriaBll categoriaBll = new CategoriaBll();

        // limpar database
        categoriaBll.limparBancoDados();

        // busca todas as categorias
        categoriaBll.getAllCategorias();

        // compara as quantidades encontradas
        assertEquals(0, categoriaBll.getAllCategorias().size());
    }
}
