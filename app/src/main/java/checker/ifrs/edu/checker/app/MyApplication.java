package checker.ifrs.edu.checker.app;

import android.app.Application;
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

                        // dados de categoria
                        Categoria categoria1 = realm.createObject(Categoria.class, 1);
                        categoria1.setNome("Calçadas");

                        Categoria categoria2 = realm.createObject(Categoria.class, 2);
                        categoria2.setNome("Rebaixamento de calçadas");

                        realm.copyToRealm(new Categoria(3, "Coletores"));
                        realm.copyToRealm(new Categoria(4, "Circulação externa"));
                        realm.copyToRealm(new Categoria(5, "Circulação interna"));
                        realm.copyToRealm(new Categoria(6, "Portas"));
                        realm.copyToRealm(new Categoria(7, "Refeitório e cantina"));
                        realm.copyToRealm(new Categoria(8, "Mobiliário e layout"));
                        realm.copyToRealm(new Categoria(9, "Biblioteca"));
                        realm.copyToRealm(new Categoria(10, "Bebedouro"));
                        realm.copyToRealm(new Categoria(11, "Circulação vertical"));
                        realm.copyToRealm(new Categoria(12, "Plataforma elevatória"));
                        realm.copyToRealm(new Categoria(13, "Escada"));
                        realm.copyToRealm(new Categoria(14, "Rampas"));
                        realm.copyToRealm(new Categoria(15, "Corrimão e guarda-corpo"));
                        realm.copyToRealm(new Categoria(16, "Circulação vertical"));
                        realm.copyToRealm(new Categoria(17, "Sanitários e vestiários"));
                        realm.copyToRealm(new Categoria(18, "Cinamas, teatros, auditórios e similares"));
                        realm.copyToRealm(new Categoria(19, "Estacionamento"));
                        realm.copyToRealm(new Categoria(20, "Geral"));


                        // dados questoes
                        Questao questao1 = realm.createObject(Questao.class, 1);
                        questao1.setPergunta("Você gosta de filmes?");
                        questao1.setFeedbackCorreto("Bom garoto!");
                        questao1.setFeedbackErrado("Reveja seus gostos!");
                        questao1.setFeedbackParcial("tanto faz");
                        questao1.setCategoria(categoria1);

                        Questao questao2 = realm.createObject(Questao.class, 2);
                        questao2.setPergunta("Você gosta de sorvete?");
                        questao2.setFeedbackCorreto("Isso ai!");
                        questao2.setFeedbackErrado("Como assim? =/");
                        questao2.setFeedbackParcial("tanto faz 2");
                        questao2.setCategoria(categoria2);

                    }
                })
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
