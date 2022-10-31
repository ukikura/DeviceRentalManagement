/**
 * 
 */
package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Devices;

public interface DeviceRepository extends
 JpaRepository<Devices, Integer>,
 DeviceSearchRepository{

	@Query("select d from Devices d where d.deleteFlag=false order by d.updateDate desc")
	public List<Devices> findDeviceList();
	@Query("select count(d.id) from Devices d where d.deleteFlag=false order by d.updateDate desc")
	public int countDeviceList();

	public Devices findByDeviceId(Integer deviceId);
	
	@Query("select d from Devices d where d.deleteFlag=false and d.deviceName like ?1")
	public Optional<Devices> findByDeviceNameOptional(String deviceName);
	
	public Devices findByDeviceName(String deviceName);
	
	@Query("select d from Devices d where d.deleteFlag=false and d.available=true and d.type=?1 order by d.deviceName")
	public List<Devices> findAvailableDevice(int type);
	
	@Query("select count(d.id) from Devices d where d.available=true and d.type=?1 and d.brokenFlag=false and d.deleteFlag=false")
	public int countAvailableDevices(int type);
	
}
