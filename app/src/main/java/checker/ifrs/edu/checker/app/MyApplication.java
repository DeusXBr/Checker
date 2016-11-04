package checker.ifrs.edu.checker.app;

import android.app.Application;
import android.widget.Toast;

import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.model.dal.CategoriaDal;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("my-database.realm")      // nome do arquivo do banco de dados realm
                .deleteRealmIfMigrationNeeded() // deleta todos os dados quando trocar a versão do db
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(new Categoria(1, "Calçadas"));
                        realm.copyToRealm(new Categoria(2, "Rebaixamento de calçadas"));
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
                    }
                })
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
