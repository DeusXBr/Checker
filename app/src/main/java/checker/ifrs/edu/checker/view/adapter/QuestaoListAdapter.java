package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Questao;

public class QuestaoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Questao> mQuestaoList;

    public QuestaoListAdapter(Context mContext, List<Questao> mQuestaoList) {
        this.mContext = mContext;
        this.mQuestaoList = mQuestaoList;
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

        View v = View.inflate(mContext, R.layout.item_listview_questao, null); // set o layout de cada item

        TextView textViewNumeracao = (TextView) v.findViewById(R.id.textview_numeracao); //recupera objeto do layout
        TextView textViewPergunta = (TextView) v.findViewById(R.id.textview_pergunta); //recupera objeto do layout

        textViewNumeracao.setText(" " + (position + 1)); // coloca a numeracao das perguntas
        textViewPergunta.setText(mQuestaoList.get(position).getPergunta()); // set o text do textView

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiogroup_linha);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checked);

                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    Log.i("MeuTeste", "RadioButton foi checked");
                }
            }
        });

        return v;
    }
}
