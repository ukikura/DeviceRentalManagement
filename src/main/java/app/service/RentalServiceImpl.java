package app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Devices;
import app.entity.Rental;
import app.entity.Users;
import app.form.RentalForm;
import app.repository.DeviceRepository;
import app.repository.RentalRepository;
import app.repository.UserRepository;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<Rental> findRentalHistory() {
		List<Rental> rentalList = rentalRepository.findRentalHistory();
		return rentalList;
	}
	public List<Rental> findRentalHistory(String value) {
		String searchStr = "%" + value + "%";
		List<Rental> rentalList = rentalRepository.findRentalHistory(searchStr);
		return rentalList;
	}

	public void save(RentalForm rentalForm) {

		/*貸出情報をDBに登録します。
		 * @param rentalForm 貸出情報
		 */
		
		Rental rental = new Rental();
		Devices device = deviceRepository.findByDeviceName(rentalForm.getDeviceName());
		Users user = userRepository.findByStaffId(rentalForm.getStaffId());
		rental.setUserId(user.getUserId());
		rental.setDeviceId(device.getDeviceId());
		rental.setRentalStart(rentalForm.getRentalStart());
		rental.setRentalEnd(rentalForm.getRentalEnd());
		rental.setPlace(rentalForm.getPlace());
		rental.setRentalRemarks(rentalForm.getRentalRemarks());
		rental.setReturnDate(null);
		rentalRepository.save(rental);
		device.setAvailable(false);
		deviceRepository.save(device);
	}
	public void update(RentalForm rentalForm) {
		
		/*DBに存在する貸出情報に返却日を追加して更新します。
		 * @param rentalForm 貸出情報
		 * @param rentalId 主キー
		 */
		
		Rental rental = rentalRepository.getReferenceById(rentalForm.getRentalId());
		rental.setReturnDate(new Date());
		
		rentalRepository.save(rental);
	}
	
	public Rental findByRentalId(Integer rentalId) {
		Rental rentalList = rentalRepository.findByRentalId(rentalId);
		return rentalList;
	}

	public List<Rental> findList(String value) {
		String searchStr = "%" + value + "%";
		List<Rental> rentalList = rentalRepository.findList(searchStr);
		return rentalList;
	}

	public List<Rental> findRentalList() {
		List<Rental> rentalList = rentalRepository.findRentalList();
		return rentalList;
	}

	public List<Rental> findUserHistory(String staffId) {
		List<Rental> rentalList = rentalRepository.findUserHistrory(staffId);
		return rentalList;
	}

	public List<Rental> findTop5ByUserIdIsOrderByRentalIdDesc(Integer userId) {
		List<Rental> rentalList = rentalRepository.findTop5ByUserIdIsOrderByRentalIdDesc(userId);
		return rentalList;
	}



}
