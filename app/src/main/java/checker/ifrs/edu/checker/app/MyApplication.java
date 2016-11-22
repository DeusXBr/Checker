package checker.ifrs.edu.checker.app;

import android.app.Application;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Questao;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("my-database.realm")      // nome do arquivo do banco de dados realm
                .deleteRealmIfMigrationNeeded() // deleta todos os dados quando trocado a versão do db
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try {
                            AssetManager assetManager = getAssets();

                            InputStream is = assetManager.open("categoria.json");
                            realm.createOrUpdateAllFromJson(Categoria.class, is);

                            is = assetManager.open("questoes.json");
                            realm.createOrUpdateAllFromJson(Questao.class, is);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        // Perguntas da categoria 1
//                        Questao questao1 = realm.createObject(Questao.class, 1);
//                        questao1.setPergunta("Tem largura mínima de 120cm (circulação de uma pessoa em pé e outra em uma cadeira de rodas)?");
//                        questao1.setFeedbackCorreto("Bom garoto!");
//                        questao1.setFeedbackErrado("Reveja seus gostos!");
//                        questao1.setFeedbackParcial("tanto faz");
//                        questao1.setCategoria(categoria1);
//
//                        Questao questao2 = realm.createObject(Questao.class, 2);
//                        questao2.setPergunta("No caso de faixa gramada junto ao meio fio, a faixa pavimentada tem largura mínima de 150cm?");
//                        questao2.setFeedbackCorreto("Isso ai!");
//                        questao2.setFeedbackErrado("Como assim? =/");
//                        questao2.setFeedbackParcial("tanto faz 2");
//                        questao2.setCategoria(categoria1);
//
//                        Questao questao3 = realm.createObject(Questao.class, 3);
//                        questao3.setPergunta("O Revestimento no piso é antiderrapante?");
//                        questao3.setFeedbackCorreto("Isso ai!");
//                        questao3.setFeedbackErrado("Como assim? =/");
//                        questao3.setFeedbackParcial("tanto faz 2");
//                        questao3.setCategoria(categoria1);
//
//                        Questao questao4 = realm.createObject(Questao.class, 4);
//                        questao4.setPergunta("O Revestimento do piso é contínuo, sem ressaltos ou depressões?");
//                        questao4.setFeedbackCorreto("Isso ai!");
//                        questao4.setFeedbackErrado("Como assim? =/");
//                        questao4.setFeedbackParcial("tanto faz 2");
//                        questao4.setCategoria(categoria1);
//
//                        Questao questao5 = realm.createObject(Questao.class, 5);
//                        questao5.setPergunta("O Revestimento no piso tem superfície regular, firme e estável, sem provocar trepidações?");
//                        questao5.setFeedbackCorreto("Isso ai!");
//                        questao5.setFeedbackErrado("Como assim? =/");
//                        questao5.setFeedbackParcial("tanto faz 2");
//                        questao5.setCategoria(categoria1);
//
//                        Questao questao6 = realm.createObject(Questao.class, 6);
//                        questao6.setPergunta("A Inclinação transversal do piso é de no máximo 3%?");
//                        questao6.setFeedbackCorreto("Isso ai!");
//                        questao6.setFeedbackErrado("Como assim? =/");
//                        questao6.setFeedbackParcial("tanto faz 2");
//                        questao6.setCategoria(categoria1);
//
//                        Questao questao7 = realm.createObject(Questao.class, 7);
//                        questao7.setPergunta("A calçada possui inclinação longitudinal de até 5%?");
//                        questao7.setFeedbackCorreto("Isso ai!");
//                        questao7.setFeedbackErrado("Como assim? =/");
//                        questao7.setFeedbackParcial("tanto faz 2");
//                        questao7.setCategoria(categoria1);
//
//                        Questao questao8 = realm.createObject(Questao.class, 8);
//                        questao8.setPergunta("Se a calçada possui inclinação longitudinal maior que 5%, esta inclinação é menor que 12,5%?");
//                        questao8.setFeedbackCorreto("Isso ai!");
//                        questao8.setFeedbackErrado("Como assim? =/");
//                        questao8.setFeedbackParcial("tanto faz 2");
//                        questao8.setCategoria(categoria1);
//
//                        Questao questao9 = realm.createObject(Questao.class, 9);
//                        questao9.setPergunta("A calçada tem inclinação contínua?");
//                        questao9.setFeedbackCorreto("Isso ai!");
//                        questao9.setFeedbackErrado("Como assim? =/");
//                        questao9.setFeedbackParcial("tanto faz 2");
//                        questao9.setCategoria(categoria1);
//
//                        Questao questao10 = realm.createObject(Questao.class, 10);
//                        questao10.setPergunta("Se existirem degraus em qualquer das inclinações, assinale a opção não.");
//                        questao10.setFeedbackCorreto("Isso ai!");
//                        questao10.setFeedbackErrado("Como assim? =/");
//                        questao10.setFeedbackParcial("tanto faz 2");
//                        questao10.setCategoria(categoria1);



                        // Perguntas da categoria 2
//                        Questao questao19 = realm.createObject(Questao.class, 19);
//                        questao19.setPergunta("Nas calçadas em locais com faixa destinada á travessia de via pública por pedestres, há rebaixamento do meio-fio e rampa sobre a calçada?");
//                        questao19.setFeedbackCorreto("Isso ai!");
//                        questao19.setFeedbackErrado("Como assim? =/");
//                        questao19.setFeedbackParcial("tanto faz 2");
//                        questao19.setCategoria(categoria2);
//
//                        Questao questao20 = realm.createObject(Questao.class, 20);
//                        questao20.setPergunta("Há faixa de circulação plana livre e contínua na calçada em frente à rampa?");
//                        questao20.setFeedbackCorreto("Isso ai!");
//                        questao20.setFeedbackErrado("Como assim? =/");
//                        questao20.setFeedbackParcial("tanto faz 2");
//                        questao20.setCategoria(categoria2);


                        // Perguntas da categoria 3
//                        Questao questao28 = realm.createObject(Questao.class, 28);
//                        questao28.setPergunta("Nas grades e ralos, o espaço máximo entre barras é de 1,50cm?");
//                        questao28.setFeedbackCorreto("Isso ai!");
//                        questao28.setFeedbackErrado("Como assim? =/");
//                        questao28.setFeedbackParcial("tanto faz 2");
//                        questao28.setCategoria(categoria3);
//
//                        Questao questao29 = realm.createObject(Questao.class, 29);
//                        questao29.setPergunta("As grelhas são embutidas no piso, sem alterar o nivelamento deste?");
//                        questao29.setFeedbackCorreto("Isso ai!");
//                        questao29.setFeedbackErrado("Como assim? =/");
//                        questao29.setFeedbackParcial("tanto faz 2");
//                        questao29.setCategoria(categoria3);


                    }
                })
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
