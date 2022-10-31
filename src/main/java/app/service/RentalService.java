package app.service;

import java.util.List;

import app.entity.Rental;
import app.form.RentalForm;


public interface RentalService {
	/**
	 * DBに登録されたすべての貸出履歴リストを取得します。
	 * @return 貸出履歴一覧
	 */
	public List<Rental> findRentalHistory();

	/**
	 * DBに登録された貸出履歴から検索内容が一致するものの一覧を取得します。
	 * @param value 検索キーワード
	 * @return 貸出履歴一覧
	 */
	public List<Rental> findRentalHistory(String searchStr);

	/*
	 * DBに登録された貸出履歴リストのうち、利用者が一致するリストの最新10件取得します。
	 * @return 貸出履歴一覧
	 */
	public List<Rental> findTop5ByUserIdIsOrderByRentalIdDesc(Integer userId);
	/**
	 * 貸出情報をDBに登録します。
	 * @param rentalForm 入力した貸出情報
	 */
	public void save(RentalForm rentalForm);
	/**
	 * DBに登録された貸出情報からrentalIdが一致するものを取得します。
	 * @param rentalId 貸出情報の主キー
	 * @return 貸出一覧
	 */
	public Rental findByRentalId(Integer rentalId);
	/*
	 * 返却済みに更新してDBに登録します。
	 * @param rentalForm 貸出情報
	 * @param rentalId 主キー
	 */
	public void update(RentalForm rentalForm);
	/**
	 * DBに登録された貸出情報から検索内容が一致するものの一覧を取得します。
	 * @param value 検索キーワード
	 * @return 貸出情報一覧
	 */
	public List<Rental> findList(String searchStr);
	/**
	 * DBに登録された貸出情報から機器に重複のない一覧を取得します。
	 * @return 貸出情報一覧
	 */
	public List<Rental> findRentalList();
	/**
	 * DBに登録された貸出履歴のうち、利用者が一致するリストを取得します。
	 * @param userId userId
	 * @return 貸出履歴一覧
	 */
	public List<Rental> findUserHistory(String staffId);

}
