package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.entity.Users;
import crm_project_02.repository.RoleRepository;
import crm_project_02.repository.UserRepository;

	public class UserService {
	 
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();
	
	
	public List<Role> getAllRole(){
		return roleRepository.getAllRole();
	}
	
	public boolean insertUser(String firtname, String lastname, String fullname, String username , String pwd, String phone, String email, int idRole) {
		
		int count = userRepository.insert(firtname, lastname, fullname, username, pwd, phone, email, idRole);
		 
		return count > 0;
	}
	
	
	public List<Users> getAllUsers(){
		
		return userRepository.getAllUsers();
	}

	public boolean deleteUser(int id) {
		int count = userRepository.deleteById(id);
		
		return count > 0;
	}
	
	public boolean updateUser(String firtname, String lastname, String fullname, String username , String pwd, String phone, String email, int idRole, int id) {
		
		int count = userRepository.updateUser(firtname, lastname, fullname, username, pwd, phone, email, idRole, id);
		
		return count > 0;
		
	}
	
}