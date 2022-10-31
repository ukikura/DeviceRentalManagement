package app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Devices;
import app.form.DeviceForm;
import app.form.DeviceSearchForm;
import app.repository.DeviceRepository;
import app.repository.DeviceSearchRepository;
import app.repository.DeviceSearchRepositoryImpl;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	DeviceSearchRepository deviceSearchRepository;
	
	public List<Devices> findDeviceList() {
		List<Devices> deviceList = deviceRepository.findDeviceList();
		return deviceList;
	}

	public void save(DeviceForm deviceForm) {

		/*機器情報をDBに登録します。
		 * @param deviceForm 機器情報
		 */
		Devices device = new Devices();
		device.setDeviceName(deviceForm.getDeviceName());
		device.setType(deviceForm.getType());
		device.setBrokenFlag(deviceForm.isBrokenFlag());
		device.setInventoryDate(deviceForm.getInventoryDate());
		device.setDeviceRemarks(deviceForm.getDeviceRemarks());
		device.setDeleteFlag(deviceForm.isDeleteFlag());
		device.setUpdateDate(deviceForm.getUpdateDate());
		device.setMaker(deviceForm.getMaker());
		if(deviceForm.getType()==0) {			
			device.setOs(deviceForm.getOs());
			device.setMemory(deviceForm.getMemory());
			device.setCapacity(deviceForm.getCapacity());
			device.setGraphicBoard(deviceForm.isGraphicBoard());
		}else {
			device.setOs("");
			device.setMemory(0);
			device.setCapacity(0);
			device.setGraphicBoard(false);
			
		}
		device.setRoom(deviceForm.getRoom());
		if(deviceForm.isBrokenFlag()) {
			device.setAvailable(false);
		}else {
			device.setAvailable(true);
		}
		device.setRegistrationDate(new Date());
		deviceRepository.save(device);
	}
	public void update(DeviceForm deviceForm, Integer deviceId) {
		
		/*DBに存在する機器情報を更新します。
		 * @param deviceForm 機器情報
		 * @param deviceId 主キー
		 */
		Devices device = deviceRepository.getReferenceById(deviceId);
		device.setDeviceId(deviceForm.getDeviceId());
		device.setDeviceName(deviceForm.getDeviceName());
		device.setType(deviceForm.getType());
		device.setBrokenFlag(deviceForm.isBrokenFlag());
		device.setInventoryDate(deviceForm.getInventoryDate());
		device.setDeviceRemarks(deviceForm.getDeviceRemarks());
		device.setDeleteFlag(deviceForm.isDeleteFlag());
		device.setUpdateDate(deviceForm.getUpdateDate());
		if(deviceForm.getType()==1) {			
			device.setOs(deviceForm.getOs());
			device.setMemory(deviceForm.getMemory());
			device.setMaker(deviceForm.getMaker());
			device.setCapacity(deviceForm.getCapacity());
			device.setGraphicBoard(deviceForm.isGraphicBoard());
		}else {
			device.setOs("");
			device.setMemory(0);
			device.setMaker("");
			device.setCapacity(0);
			device.setGraphicBoard(false);
		}
		device.setRoom(deviceForm.getRoom());
		device.setRoom(deviceForm.getRoom());
		if(deviceForm.isBrokenFlag()) {
			device.setAvailable(false);
		}else {
			device.setAvailable(true);
		}
		device.setRegistrationDate(deviceForm.getRegistrationDate());
		deviceRepository.save(device);
	}
	
	public Devices findByDeviceId(Integer deviceId) {
		Devices deviceList = deviceRepository.findByDeviceId(deviceId);
		return deviceList;
	}

	public void saveDeleteFlag(Integer deviceId) {
		Devices deviceToUpdate = deviceRepository.getReferenceById(deviceId);
		deviceToUpdate.setDeleteFlag(true);
		deviceRepository.save(deviceToUpdate);
	}

	public Optional<Devices> findByDeviceNameOptional(String deviceName) {
		Optional<Devices> device = deviceRepository.findByDeviceNameOptional(deviceName);
        return device;
	}
/*
	public List<Devices> findListByDeviceName(DeviceSearchForm deviceName) {
		String value = "%" + deviceName + "%";
		List<Devices> deviceList = deviceRepository.findListByDeviceName(value);
		return deviceList;
	}
*/
	
	public List<Devices> findSearchResult(DeviceSearchForm deviceSearchForm){
		deviceSearchRepository = new DeviceSearchRepositoryImpl();
		List<Devices> deviceList = deviceRepository.findSearchResult(deviceSearchForm);
		return deviceList;
		
	}

	@Override
	public List<Devices> findAvailableDevice(int type) {
		List<Devices> deviceList = deviceRepository.findAvailableDevice(type);
		return deviceList;
	}

	public void update(int deviceId, String deviceRemarks, boolean brokenFlag) {
		Devices deviceToUpdate = deviceRepository.getReferenceById(deviceId);
		deviceToUpdate.setBrokenFlag(brokenFlag);
		System.out.println(deviceRemarks);
		if(deviceRemarks==null) {
			deviceToUpdate.setDeviceRemarks("");
		} else {
			deviceToUpdate.setDeviceRemarks(deviceRemarks);
		}
		deviceToUpdate.setAvailable(true);
		deviceRepository.save(deviceToUpdate);
	}

	public int countAvailableDevices(int type) {
		int countDevices = deviceRepository.countAvailableDevices(type);
		return countDevices;
	}

	public int countDeviceList() {
		int countDevices = deviceRepository.countDeviceList();
		return countDevices;
	}

}
