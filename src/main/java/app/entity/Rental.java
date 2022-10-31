package app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="rental")
@NamedEntityGraph(
        name = "group_rental",
        includeAllAttributes = true
)public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer rentalId;
	@Column(name="device_id")
	private int deviceId;
	@Column(name="user_id")
	@JoinColumn(table="users", name="id")
	private int userId;
	@Column(name="rental_start")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rentalStart;
	@Column(name="rental_end")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rentalEnd;
	@Column(name="place")
	private String place;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name="return_date")
	private Date returnDate;
	@Column(name="rental_remarks")
	private String rentalRemarks;
	
	@ManyToOne
	@JoinColumn(name="device_id", referencedColumnName="id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Devices device;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Users user;
}

