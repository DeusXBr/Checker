package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.QuestaoBll;
import checker.ifrs.edu.checker.model.bll.RespostaBll;
import checker.ifrs.edu.checker.view.activities.MainActivity;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Questao;
import checker.ifrs.edu.checker.vo.RealmInt;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.RealmList;

public class QuestaoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Questao> mQuestaoList;
    private Map<Integer, Integer> myMap;

    public QuestaoListAdapter(Context mContext, List<Questao> mQuestaoList) {
        this.mContext = mContext;
        this.mQuestaoList = mQuestaoList;

        this.myMap = new HashMap<Integer, Integer>();
    }

    @Override
    public int getCount() {
        return mQuestaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuestaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        //TODO arrumar layout -> item_listview_questao.xml, texto cortado

        // pega os dados do sharedPreference
        SharedPreferences sharedPrefs = mContext.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        final String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);

        View v = View.inflate(mContext, R.layout.item_listview_questao, null); // set o layout de cada item

        TextView textViewNumeracao = (TextView) v.findViewById(R.id.textview_numeracao); //recupera objeto do layout
        TextView textViewPergunta = (TextView) v.findViewById(R.id.textview_pergunta); //recupera objeto do layout

        textViewNumeracao.setText(" " + (position + 1)); // coloca a numeracao das perguntas
        textViewPergunta.setText(mQuestaoList.get(position).getPergunta()); // set o text do textView

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiogroup_linha);

        if( myMap.get(mQuestaoList.get(position).getId()) != null ){
            radioGroup.check(myMap.get(mQuestaoList.get(position).getId()));
        }
//        if(listRespostasView.size() != 0){
//            int result = listRespostasView.get(position);
//            RadioButton rb = (RadioButton)radioGroup.getChildAt(result);
//            if(rb != null){
//                rb.setChecked(true);
//            }
//        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checked);

                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {

                    AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
                    Avaliacao avaliacao = avaliacaoBll.getAvaliacao(avaliacaoTitulo);

                    QuestaoBll questaoBll = new QuestaoBll();
                    Questao questao = questaoBll.getQuestao(mQuestaoList.get(position).getId());

                    RealmInt realmInt = new RealmInt();
                    realmInt.setValor(checkedRadioButton.getText().toString());

                    Log.i("MeuTeste", "checkRadioButton" + checkedRadioButton.getId());

                    myMap.put(mQuestaoList.get(position).getId(), checkedRadioButton.getId());

                    // verificar se a resposta ja está criada
                    RespostaBll respostaBll = new RespostaBll();
                    Resposta resposta = respostaBll.getResposta(avaliacao);

                    if(resposta != null){
                        resposta.setQuestoes(questao);
                        resposta.setInts(realmInt);
                    }else{
                        resposta = new Resposta(avaliacao, questao, realmInt);
                        respostaBll.addResposta(resposta);
                    }

                    Log.i("MeuTeste", "Avaliacao: " + resposta.getAvaliacao().getNome());
                    Log.i("MeuTeste", "Questão: " + resposta.getQuestoes().size());
                    Log.i("MeuTeste", "Resposta: " + resposta.getInts().size());
                }
            }
        });


        return v;
    }
}
