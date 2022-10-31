package app.service;

import java.util.List;
import java.util.Optional;

import app.entity.Users;
import app.form.UserForm;


public interface UserService {
	/**
	 * DBに登録されたすべての利用者情報のうち、削除フラグがオフのリストを取得します。
	 * @return 利用者一覧
	 */
	public List<Users> findUserList();
	/**
	 * 利用者情報をDBに登録します。
	 * @param userForm 入力した利用者情報
	 */
	public void save(UserForm userForm);
	/**
	 * DBに登録された利用者情報からuserIdが一致するものを取得します。
	 * @param userId 利用者情報の主キー
	 * @return 利用者一覧
	 */
	public Users findByUserId(Integer userId);
	/*
	 * 特定の利用者情報の削除フラグをオンに変更します。
	 * @param user 利用者情報
	 */
	public void saveDeleteFlag(Integer userId);
	/*
	 * 更新した特定の利用者情報をDBに登録します。
	 * @param userForm 利用者情報
	 * @param userId 主キー
	 */
	public void update(UserForm userForm);
	/**
	 * DBに登録された利用者情報から社員番号が一致するものを取得します。
	 * @param StaffId 社員番号
	 * @return 利用者情報
	 */
	public Optional<Users> findByStaffIdOptional(String StaffId);
	/**
	 * DBに登録された利用者情報からキーワードを含むものの一覧を取得します。
	 * @param 検索キーワード
	 * @return 利用者一覧
	 */
	public List<Users> findList(String value);

}
