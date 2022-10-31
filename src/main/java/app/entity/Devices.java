package app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="devices")
public class Devices {
	@PrimaryKeyJoinColumn
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer deviceId;
	@Column(name="device_name")
	private String deviceName;
	@Column(name="type")
	private int type;
	@Column(name="broken_flag")
	private boolean brokenFlag;
	@Column(name="inventory_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inventoryDate;
	@Column(name="device_remarks")
	private String deviceRemarks;
	@Column(name="delete_flag")
	private boolean deleteFlag;
	@Column(name="registration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date registrationDate;
	@Column(name="update_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateDate;
	@Column(name="maker")
	private String maker;
	@Column(name="os")
	private String os;
	@Column(name="memory")
	private int memory;
	@Column(name="capacity")
	private int capacity;
	@Column(name="graphic_board")
	private boolean graphicBoard;
	@Column(name="room")
	private String room;
	@Column(name="available")
	private boolean available;
}
