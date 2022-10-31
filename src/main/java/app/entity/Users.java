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
@Table(name="users")
public class Users {
	@PrimaryKeyJoinColumn
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer userId;
	@Column(name="staff_id")
	private String staffId;
	@Column(name="name")
	private String name;
	@Column(name="birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@Column(name="gender")
	private int gender;
	@Column(name="tel_number")
	private String telNumber;
	@Column(name="mail_address")
	private String mailAddress;
	@Column(name="retirement_flag")
	private boolean retirementFlag;
	@Column(name="retirement_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date retirementDate;
	@Column(name="department")
	private String department;
	@Column(name="user_remarks")
	private String userRemarks;
	@Column(name="delete_flag")
	private boolean deleteFlag;
	@Column(name="registration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date registrationDate;
	@Column(name="update_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateDate;
	@Column(name="admin")
	private boolean admin;
}
