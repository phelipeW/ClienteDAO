package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
@Builder
public class ClienteDTO {
    private int id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    public ClienteDTO(){
		
	}
	
	public ClienteDTO(int id, String nome, String telefone, int idade, double limitecredito, int idPais){
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.limiteCredito = limiteCredito;
		this.pais.setId(idPais);
	}
	
	
	public void setNome(String nome) throws NomeClienteMenor5CaracteresException {
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);

        this.nome = nome;
    }
	
	
    
}