package crm_project_02.service;

import crm_project_02.repository.LoginRepository;

public class LoginService {
	
	private LoginRepository loginRepository = new LoginRepository();
	
	public boolean loginSystem(String email, String password ) {
		int count = loginRepository.loginSystem(email, password);
		
		return count > 0;
	}
}
