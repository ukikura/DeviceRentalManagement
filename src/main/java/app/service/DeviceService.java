package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.Devices;
import app.form.DeviceForm;
import app.form.DeviceSearchForm;


public interface DeviceService {
	/**
	 * DBに登録されたすべての機器情報のうち、削除フラグがオフのリストを取得します。
	 * @return 機器一覧
	 */
	public List<Devices> findDeviceList();
	
	/*
	 * DBに登録されたすべての機器情報のうち、削除フラグがオフの機器の個数を返します。
	 * @return リスト行数
	 */
	public int countDeviceList();

	/**
	 * 機器情報をDBに登録します。
	 * @param deviceForm 入力した機器情報
	 */
	public void save(DeviceForm deviceForm);
	/**
	 * DBに登録された機器情報からdeviceIdが一致するものを取得します。
	 * @param deviceId 機器情報の主キー
	 * @return 機器一覧
	 */
	public Devices findByDeviceId(Integer deviceId);
	/*
	 * 特定の機器情報の削除フラグをオンに変更します。
	 * @param device 機器情報
	 */
	public void saveDeleteFlag(Integer deviceId);
	/*
	 * 更新した特定の機器情報をDBに登録します。
	 * @param deviceForm 機器情報
	 * @param deviceId 主キー
	 */
	public void update(DeviceForm deviceForm, Integer deviceId);
	/**
	 * DBに登録された機器情報からdeviceNameが一致するものを取得します。
	 * @param deviceId 機器情報の主キー
	 * @return 機器情報
	 */
	public Optional<Devices> findByDeviceNameOptional(String deviceName);
	
	 /**
	 * DBに登録された機器情報からdeviceNameが一致するものの一覧を取得します。
	 * @param deviceId 機器情報の主キー
	 * @return 機器一覧
	 */
	/*
	public List<Devices> findListByDeviceName(String deviceName);
	*/
	/**
	 * DBに登録された機器情報から貸出可能な機器一覧を取得します。
	 * @param type 
	 * @return 機器一覧
	 */
	public List<Devices> findAvailableDevice(int type);
	/**
	 * 特定の機器の備考と故障フラグを更新します。
	 * @param deviceRemarkes 備考欄内容
	 * @param brokenFlag 故障フラグ
	 */
	public void update(int deviceId, String deviceRemarkes, boolean brokenFlag);
	 /*
	 * DBに登録された機器情報から検索条件に一致するものの一覧を取得します。
	 * @param deviceSearchForm 検索フォームの入力内容
	 * @return 機器一覧
	 */
	public List<Devices> findSearchResult(DeviceSearchForm deviceSearchForm);
	/*
	 * DBに登録された機器のうち指定したタイプで利用可能な機器の数を取得します。
	 * @param type 機器タイプ
	 * preturn 利用可能な機器数
	 */
	public int countAvailableDevices(int type);
}
