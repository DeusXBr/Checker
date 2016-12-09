package checker.ifrs.edu.checker.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_EXTRA = "checker.ifrs.edu.checker.TITULO_AVALIACAO";
    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.close, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        finish();
        return true;
    }

    /**
     * Callback
     * @param view
     */
    public void novaAvaliacao(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(promptsView);

        final EditText titulo = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        builder.setPositiveButton("criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }

        })
            .setNegativeButton("Cancelar", null);

        final AlertDialog alert = builder.create();
        alert.show();

        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText(titulo.getText().toString().trim()); // retira espacos em branco do inicio e do fim

                AvaliacaoBll mAvaliacaoBll = new AvaliacaoBll();

                // verifica se foi preenchido o titulo
                if (titulo.getText().toString().length() > 0) {

                    Avaliacao resultAvaliacao = mAvaliacaoBll.getAvaliacao(titulo.getText().toString());

                    // verifica se existe uma avaliacao com o mesmo titulo no realm
                    if( resultAvaliacao == null ){

                        // cria nova avaliacao
                        Avaliacao avaliacao = new Avaliacao();
                        avaliacao.setNome(titulo.getText().toString());
                        avaliacao.setEstado("Novo");

                        mAvaliacaoBll.addAvaliacao(avaliacao); // adiciona no realm

                        // titulo da avaliacao salva no sharedPreferences
                        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putInt("avaliacaoId", avaliacao.getId());
                        editor.putString("avaliacaoTitulo", avaliacao.getNome());
                        editor.apply();

                        // Intent para nova activity
                        Intent intent = new Intent(MainActivity.this, AvaliacaoActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Título já existe.",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Título não pode ficar em branco.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Callback
     * @param view
     */
    public void listarAvaliacoes(View view) {
        Intent intent = new Intent(MainActivity.this, ListaRelatorioActivity.class);
        startActivity(intent);
    }

    /**
     * Callback
     * @param view
     */
    public void sobre(View view) {
        Intent intent = new Intent(MainActivity.this, SobreActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo para inicializar o toolbar
     *
     * Obs: chamada esta no construtor da classe
     *
     */
    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.main_toolbar_title);

        // Coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
    }

}
