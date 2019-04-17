package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.ClienteDTO;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE Cliente (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"telefone varchar(30)," + 
						"idade int," + 
                        "limiteCredito double," +
                        "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
    }
	
	public void addCliente(ClienteDTO cliente){
		String sql = "INSERT INTO Cliente (id, nome, telefone, idade, limiteCredito, idPais) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, cliente.id);
		statement.setString(2, cliente.nome);
		statement.setString(3, cliente.telefone);
		statement.setInt(4, cliente.idade);
		statement.setDouble(5, cliente.limiteCredito);
		statement.setInt(6, cliente.idPais);
		 
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario inserido com sucesso!!");
		}
	}
	
	public List<ClienteDTO> ListarTodos(){
		
		List<ClienteDTO> lista = new List<ClienteDTO>;
		
		String sql = "SELECT * FROM Users";
		
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		 
		int count = 0;
		 
		while (result.next()){
			int id = result.getId(1)
			String nome = result.getString(2);
			String telefone = result.getString(3);
			int idade = result.getInt(4);
			double limiteCredito = result.getDouble(5);
			int idPais = result.getInt(6)
		 
			ClienteDTO c = new ClienteDTO(id,nome,telefone,idade,limiteCredito,idPais);
			lista.add(c);
		}
		
		return lista;
	}
	
	//recebe um cliente com os dados alterados e um nome para saber qual cliente está sendo alterado(nome antigo se ClienteDTO c tiver o nome alterado)
	public void AtualizarCliente(ClienteDTO c, String nome){ 
		String sql = "UPDATE Users SET nome=?, telefone=?, idade=?,limiteCredito=?,idPais=? WHERE username=?";
 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, c.getNome());
		statement.setString(2, c.getTelefone());
		statement.setInt(3, c.getIdade());
		statement.setDouble(4, c.getLimiteCredito());
		statement.setInt(5,c.getPais.getId());
		statement.setString(6,nome);
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Campo atualizado com sucesso!!");
		}
	}
	
	public void DeletarCliente(String nome){
		String sql = "DELETE FROM Users WHERE username=?";
 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, nome);
		 
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Usuario deletado com sucesso!!");
}
	}
	
	
}