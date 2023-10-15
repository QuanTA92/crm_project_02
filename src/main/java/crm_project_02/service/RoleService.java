package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.entity.Users;
import crm_project_02.repository.RoleRepository;

public class RoleService {

	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addRole(String name, String desc) {
		
		int count = roleRepository.insert(name, desc);
		
		return count > 0;
		
	}
	
	public List<Role> getAllRole(){
		
		return roleRepository.getAllRole();
	}
	
	
	public boolean deleteRole(int id) {
		
		int count = roleRepository.deleteById(id);
		
		return count > 0;
	}
	
	public boolean updateRole(String name, String desc, int id) {
		
		int count = roleRepository.updateRole(name, desc, id);
		
		return count > 0;
	}
}
