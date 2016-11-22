package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        View v = View.inflate(mContext, R.layout.item_listview_questao, null); // set o layout de cada item

        TextView textViewNumeracao = (TextView) v.findViewById(R.id.textview_numeracao); //recupera objeto do layout
        TextView textViewPergunta = (TextView) v.findViewById(R.id.textview_pergunta); //recupera objeto do layout

        textViewNumeracao.setText(" " + (position + 1)); // coloca a numeracao das perguntas
        textViewPergunta.setText(mQuestaoList.get(position).getPergunta()); // set o text do textView

        //TODO arrumar layout -> item_listview_questao.xml

        return v;
    }
}
