package checker.ifrs.edu.checker.view.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.Realm;

public class AvaliacaoActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        // Metodo para inicializar o toolbar
        initToolBar();

        CategoriaBll mCategoriaBll = new CategoriaBll();

        LinearLayout mlinearLayout = (LinearLayout) findViewById(R.id.content_avaliacao);
        ArrayList<Categoria> resultCategoria = mCategoriaBll.getAllCategorias();

        Iterator itr = resultCategoria.iterator();

        while(itr.hasNext()){
            Categoria mCategoria = (Categoria)itr.next();

            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(mCategoria.getId());
            checkBox.setText(mCategoria.getNome());
            mlinearLayout.addView(checkBox);
        }

        Button mButton = new Button(this);

        mButton.setText("Resultado");
        mlinearLayout.addView(mButton);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // acao para voltar no toolbar
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.avaliacao_toolbar_title);

        // Coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        // mostra o botao voltar no toolbar
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
