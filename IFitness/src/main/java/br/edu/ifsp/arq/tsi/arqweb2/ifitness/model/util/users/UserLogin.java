package br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.util.users;

import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.ifitness.utils.PasswordEncoder;

public class UserLogin {
	
	public static User login(String email, String password) {
		// ler o arquivo de usuários
		List<User> users = UsersReader.read();
		if(users != null) {
			for(User u: users) {
				if(u.getEmail().equals(email) && 
					u.getPassword().equals(
							PasswordEncoder.encode(password))) {
					return u;
				}
			}
		}
		return null;
	}

}




