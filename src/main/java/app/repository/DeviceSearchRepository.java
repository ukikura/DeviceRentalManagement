/**
 * 
 */
package app.repository;

import java.util.List;

import app.entity.Devices;
import app.form.DeviceSearchForm;

public interface DeviceSearchRepository{
	
	/*
	 * 動的クエリ組み立てのためのインターフェース
	 */

	public List<Devices> findSearchResult(DeviceSearchForm deviceSearchForm);
	
}
