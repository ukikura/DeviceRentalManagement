package app.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import app.UnusedDeviceName;
import app.validate.EntryCheckGroup;
import app.validate.UpdateCheckGroup;
import lombok.Data;

@Data
public class DeviceForm {
	private Integer deviceId;
	@UnusedDeviceName(message = "この資産番号は登録済みです", groups = EntryCheckGroup.class)
	@NotBlank(message = "資産番号は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@Pattern(regexp = "[0-9a-zA-Z_\\-]*", message = "資産番号は英数字で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
    @Size(max = 20, message = "資産番号は20字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String deviceName;
	private int type;
	@Size(max = 20, message = "OS名は20字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String os;
	private boolean brokenFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "未来の日付は入力できません", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	@NotNull(message = "棚卸日は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private Date inventoryDate;
    @Size(max = 255, message = "備考欄は255字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String deviceRemarks;
	private boolean deleteFlag;
    @Size(max = 20, message = "メーカーは20字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String maker;
	private int memory;
	private int capacity;
	private boolean graphicBoard;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date registrationDate;
	@NotBlank(message = "保管場所は必須項目です", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
    @Size(max = 20, message = "保管場所は20字以内で入力してください", groups = {UpdateCheckGroup.class, EntryCheckGroup.class})
	private String room;

	public DeviceForm() {
		setDeleteFlag(false);
		setDeviceRemarks("");
		setOs("");
		setMaker("");
		setUpdateDate(new Date());
	}

	

	
}
