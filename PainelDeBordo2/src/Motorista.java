public class Motorista {
    private String nome;
    private String localizacao;

    public Motorista(String nome) {
        this.nome = nome;
        this.localizacao = "Casa"; // Padrão
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean podeDirigir(Carro carro) {
        // Se o carro está no mecânico, só pode ser dirigido se o motorista também estiver no mecânico
        if (carro.getLocalizacao().equals("Mecânico") && !this.getLocalizacao().equals("Mecânico")) {
            return false;
        }

        if (carro.getProprietario().equals(this.nome)) {
            return true;
        }
        if (!carro.isCarroTrabalho()) {
            return true;
        }
        return false;
    }
}
