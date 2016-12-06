package checker.ifrs.edu.checker.vo;

public class RealmInt{

    private int valor;

    /**
     * Construtor
     */
    public RealmInt(){
    }

    public RealmInt(int valor){
        this.valor = valor;
    }

    /**
     * Set
     */
    public void setValor(int valor){
        this.valor = valor;
    }

    public void setValor(String opcao){
        opcaoStringToInt(opcao);
    }

    /**
     * Get
     */
    public int getValor(){
        return this.valor;
    }

    /**
     * Util
     */
    private void opcaoStringToInt(String opcao){
        switch (opcao){
            case "Sim":
                this.valor = 1;
                break;
            case "Não":
                this.valor = 2;
                break;
            case "Parcialmente":
                this.valor = 3;
                break;
            case "Não se aplica":
                this.valor = 4;
                break;
        }
    }
}
