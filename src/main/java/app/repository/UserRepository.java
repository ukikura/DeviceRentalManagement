package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("select u from Users u where u.deleteFlag=false order by u.updateDate desc")
	public List<Users> findUserList();
	public Users findByUserId(Integer userId);
	@Query("select u from Users u where u.deleteFlag=false and u.staffId=?1")
	public Optional<Users> findByStaffIdOptional(String staffId);
	public Users findByStaffId(String staffId);
	@Query("select u from Users u where u.deleteFlag=false"
			+ " and ("
			+ " u.staffId like ?1"
			+ " or u.name like ?1"
			+ " or u.department like ?1"
			+ " or u.telNumber like ?1"
			+ " or u.mailAddress like ?1"
			+ " ) order by u.updateDate desc")
	public List<Users> findList(String searchStr);
	
}
