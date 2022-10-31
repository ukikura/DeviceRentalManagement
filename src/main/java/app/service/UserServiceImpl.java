package app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Users;
import app.form.UserForm;
import app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<Users> findUserList() {
		List<Users> userList = userRepository.findUserList();
		return userList;
	}

	public void save(UserForm userForm) {

		/*利用者情報をDBに登録します。
		 * @param userForm 利用者情報
		 */
		Users user = new Users();
		user.setStaffId(userForm.getStaffId());
		user.setName(userForm.getName());
		user.setBirthday(userForm.getBirthday());
		user.setGender(userForm.getGender());
		user.setTelNumber(userForm.getTelNumber());
		user.setMailAddress(userForm.getMailAddress());
		user.setRetirementFlag(userForm.isRetirementFlag());
		if(userForm.isRetirementFlag()) {			
			user.setRetirementDate(userForm.getRetirementDate());
		}else {
			user.setRetirementDate(null);
		}
		user.setDepartment(userForm.getDepartment());
		user.setUserRemarks(userForm.getUserRemarks());
		user.setDeleteFlag(userForm.isDeleteFlag());
		user.setRegistrationDate(new Date());
		user.setUpdateDate(new Date());
		user.setAdmin(userForm.isAdmin());

		userRepository.save(user);
	}
	public void update(UserForm userForm) {
		
		/*DBに存在する利用者情報を更新します。
		 * @param userForm 利用者情報
		 * @param userId 主キー
		 */
		Users user = new Users();
		user.setUserId(userForm.getUserId());
		user.setStaffId(userForm.getStaffId());
		user.setName(userForm.getName());
		user.setBirthday(userForm.getBirthday());
		user.setGender(userForm.getGender());
		user.setTelNumber(userForm.getTelNumber());
		user.setMailAddress(userForm.getMailAddress());
		user.setRetirementFlag(userForm.isRetirementFlag());
		if(userForm.isRetirementFlag()) {			
			user.setRetirementDate(userForm.getRetirementDate());
		}else {
			user.setRetirementDate(null);
		}
		user.setDepartment(userForm.getDepartment());
		user.setUserRemarks(userForm.getUserRemarks());
		user.setDeleteFlag(userForm.isDeleteFlag());
		user.setRegistrationDate(userForm.getRegistrationDate());
		user.setUpdateDate(userForm.getUpdateDate());
		user.setAdmin(userForm.isAdmin());

		userRepository.save(user);
	}
	
	public Users findByUserId(Integer userId) {
		Users userList = userRepository.findByUserId(userId);
		return userList;
	}

	public void saveDeleteFlag(Integer userId) {
		Users userToUpdate = userRepository.getReferenceById(userId);
		userToUpdate.setDeleteFlag(true);
		userRepository.save(userToUpdate);
	}

	public Optional<Users> findByStaffIdOptional(String staffId) {
		Optional<Users> user = userRepository.findByStaffIdOptional(staffId);
        return user;
	}


	@Override
	public List<Users> findList(String value) {
		String searchStr = "%" + value + "%";
		List<Users> userList = userRepository.findList(searchStr);
		return userList;
	}


}
