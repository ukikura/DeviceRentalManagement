/**
 * 
 */
package app.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import app.entity.Devices;
import app.form.DeviceSearchForm;

public class DeviceSearchRepositoryImpl implements DeviceSearchRepository {
	@Autowired
    EntityManager entityManager;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/*
	 * 動的クエリ組み立てのためのインターフェース
	 */
	public List<Devices> findSearchResult(DeviceSearchForm searchForm){
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM devices WHERE delete_flag=0");
		
		if(searchForm.getSearch()!=null) {
			sqlBuilder.append(" AND device_name LIKE '%" + searchForm.getSearch() + "%'");
		}
		if(searchForm.getType()!=0) {
			sqlBuilder.append(" AND type = " + searchForm.getType());
			
		}
		if(searchForm.getMemory()!=0) {
			sqlBuilder.append(" AND memory = " + searchForm.getMemory());
			
		}
		if(searchForm.getCapacity()!=0) {
			sqlBuilder.append(" AND capacity = " + searchForm.getCapacity());
			
		}
		if(searchForm.getGraphicBoard()!=0) {
			sqlBuilder.append(" AND graphic_board = " + (searchForm.getGraphicBoard()-1));
			
		}
		if(searchForm.getStatus()==3) {
			sqlBuilder.append(" AND broken_flag = 1");
		}else if(searchForm.getStatus()==1) {
			sqlBuilder.append(" AND available = 1 AND broken_flag = 0");
		}else if(searchForm.getStatus()==2) {
			sqlBuilder.append(" AND available = 0 AND broken_flag = 0");
		}
			
		String sql = sqlBuilder.toString();
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Devices> deviceList = new ArrayList<>();
		
		for(Map<String,Object> result : resultList) {
			Devices device = new Devices();
			device.setDeviceId((Integer)result.get("id"));
			device.setDeviceName((String)result.get("device_name"));
			device.setType((int)result.get("type"));
			if((int)result.get("broken_flag")==0) {
				device.setBrokenFlag(false);
			} else {
				device.setBrokenFlag(true);
			}
			device.setInventoryDate((Date)result.get("inventory_date"));
			device.setDeviceRemarks((String)result.get("device_remarks"));
			if((int)result.get("delete_flag")==0) {
				device.setDeleteFlag(false);
			} else {
				device.setDeleteFlag(true);
			}
			device.setRegistrationDate(Date.from(ZonedDateTime.of((LocalDateTime)result.get("registration_date"), ZoneId.systemDefault()).toInstant()));
			device.setUpdateDate(Date.from(ZonedDateTime.of((LocalDateTime)result.get("update_date"), ZoneId.systemDefault()).toInstant()));
			device.setMaker((String)result.get("maker"));
			device.setOs((String)result.get("os"));
			device.setMemory((int)result.get("memory"));
			device.setCapacity((int)result.get("capacity"));
			if((int)result.get("graphic_board")!=0) {
				device.setGraphicBoard(false);
			} else {
				device.setGraphicBoard(true);
			}
			device.setRoom((String)result.get("room"));
				device.setAvailable((boolean)result.get("available"));
			
			deviceList.add(device);

		}
		
		return deviceList;
	};
	
}
