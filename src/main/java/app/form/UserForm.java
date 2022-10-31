package app.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import app.UnusedStaffId;
import app.validate.EntryCheckGroup;
import app.validate.UpdateCheckGroup;
import lombok.Data;

@Data
public class UserForm {
	private Integer userId;
	@UnusedStaffId(message = "この社員番号は登録済みです", groups = EntryCheckGroup.class)
	@NotBlank(message = "社員番号は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Pattern(regexp = "[0-9]*", message = "社員番号は数字で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Size(min = 10, message = "社員番号は10字で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
    @Size(max = 10, message = "社員番号は10字で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String staffId;
	@NotBlank(message = "氏名は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
    @Size(max = 20, message = "氏名は20字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "未来の日付は入力できません", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private Date birthday;
	private int gender;
	@NotBlank(message = "電話番号は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Pattern(regexp = "[0-9]*", message = "電話番号は数字のみで入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String telNumber;
	@Email(message = "メールアドレスの形式を確認してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Pattern(regexp = "[0-9a-zA-Z.@_\\-]*", message = "使える文字は英数字と.@_-です。", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@NotBlank(message = "メールアドレスは必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Size(max = 40, message = "メールアドレスは40字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String mailAddress;
	private boolean retirementFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date retirementDate;
	@NotBlank(message = "所属部署は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String department;
	private String userRemarks;
	private boolean deleteFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date registrationDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateDate;
	private boolean admin;
	
	public UserForm() {
		setRetirementDate(null);
		setRetirementFlag(false);
		setDeleteFlag(false);
		setUpdateDate(new Date());
	}
	
}
