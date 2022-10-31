package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
	
	@EntityGraph(value = "group_rental", type = EntityGraph.EntityGraphType.FETCH)

	@Query("select r from Rental r order by r.rentalId desc")
	public List<Rental> findRentalHistory();
	
	@Query("select r from Rental r where r.device.deviceName like ?1 or r.user.staffId like ?1 order by r.rentalId desc")
	public List<Rental> findRentalHistory(String searchStr);
	
	@Query("select r from Rental r where r.rentalId in(select max(r.rentalId) from r group by r.deviceId) and r.returnDate=null order by rentalEnd")
	public List<Rental> findRentalList();
	
	public Rental findByRentalId(Integer userId);
	
	public Optional<Rental> findByUserId(Integer userId);	
	
	@Query("select r from Rental r where r.rentalId in(select max(r.rentalId) from r group by r.deviceId) and (r.device.deviceName like ?1 or r.user.staffId like ?1 or r.user.name like ?1) and r.returnDate=null order by rentalEnd")
	public List<Rental> findList(String searchStr);
	
	@Query("select r from Rental r where r.user.staffId=?1 order by r.rentalId desc")
	public List<Rental> findUserHistrory(String staffId);
	
	public List<Rental> findTop5ByUserIdIsOrderByRentalIdDesc(Integer userId);
}
