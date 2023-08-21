package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleform.model.UsersModel;
import com.simpleform.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public UsersModel registerUser(String login,String password,String email)
	{
		if(login==null ||password==null)
		{
			return null;
		}
			else 
			{
				if(usersRepository.findFirstByLogin(login).isPresent())
				{
					System.out.println("Dulicatelogin");
					return null;
				}
			UsersModel usersModel=new UsersModel();
			usersModel.setEmail(email);
			usersModel.setId(null);
			usersModel.setLogin(login);
			usersModel.setPassword(password);
			
			 return usersRepository.save(usersModel);
		}
		
	}
	public UsersModel authenticate(String login,String password)
	{
		return usersRepository.findByLogionAndPassword(login, password).orElse(null);
	}

}
