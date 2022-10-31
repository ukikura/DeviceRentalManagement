package app.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import app.LimitMonth;
import app.NotPast;
import app.StaffExist;
import app.validate.EntryCheckGroup;
import lombok.Data;

@Data
public class RentalForm {
	@NotBlank(message = "社員番号は必須項目です", groups = EntryCheckGroup.class)
	@StaffExist(message = "登録されていない社員番号です。先に利用者登録を行ってください。", groups = EntryCheckGroup.class)
	@Pattern(regexp = "[0-9]*", message = "社員番号は数字で入力してください", groups = EntryCheckGroup.class)
    @Size(min = 10, max = 10, message = "社員番号は10字で入力してください", groups = EntryCheckGroup.class)
	private String staffId;
	private int type;
	@NotBlank(message = "資産番号は必須項目です", groups = EntryCheckGroup.class)
	private String deviceName;
	
	private int rentalId;
	private int userId;
	private int deviceId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rentalStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "返却予定日は必須項目です", groups = EntryCheckGroup.class)
	@NotPast(message = "過去の日付は入力できません", groups = EntryCheckGroup.class)
	@LimitMonth(month=3 ,message = "3ヶ月以内で指定してください", groups = EntryCheckGroup.class)
	private Date rentalEnd;
	@NotBlank(message = "利用場所は必須項目です", groups = EntryCheckGroup.class)
	private String place;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date returnDate;
	private String rentalRemarks;
	
	private boolean brokenFlag;
	@Size(max = 250, message = "250文字以内で入力してください")
	private String deviceRemarks;
	
	public RentalForm() {
		setRentalStart(new Date());
		setRentalRemarks("");
	}
	public RentalForm(Integer deviceId) {
		setRentalStart(new Date());
		setRentalRemarks("");
		setDeviceId(deviceId);
	}

	
}
