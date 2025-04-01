public class Carro extends Modelo implements Gerenciar {
    private String proprietario;
    private String localizacao;
    private boolean carroTrabalho;

    public Carro(String modelo, String cor, String proprietario, String localizacao, boolean carroTrabalho) {
        this.setModelo(modelo);
        this.setCor(cor);
        this.proprietario = proprietario;
        this.localizacao = localizacao;
        this.carroTrabalho = carroTrabalho;
    }
    //@Override indica que um metodo em uma subclasse está sobrescrevendo (ou substituindo) um metodo definido na superclasse ou em uma interface.
    @Override
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String getProprietario() {
        return proprietario;
    }

    public boolean isCarroTrabalho() {
        return carroTrabalho;
    }

    public void abrir() {
        this.setPorta(true);
        System.out.println("Porta aberta.");
    }

    public void fechar() {
        this.setPorta(false);
        System.out.println("Porta fechada.");
    }

    public void ligar() {
        if (this.isPorta()) {
            System.out.println("Cuidado! A porta do carro está aberta.");
        }
        System.out.println("Dando partida no carro...");
        this.setLigado(true);
    }

    public void desligar() {
        System.out.println("Desligando o carro...");
        this.setLigado(false);
    }

    public void status() {
        System.out.println("Carro " + this.getModelo() + " de cor " + this.getCor() +
                ". Proprietário: " + this.getProprietario() +
                ". Localização: " + this.getLocalizacao() +
                ". Porta aberta? " + this.isPorta() +
                ". Está ligado? " + this.isLigado());
    }
}
