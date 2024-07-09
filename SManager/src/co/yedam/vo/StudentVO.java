package co.yedam.vo;

import java.util.Date;

public class StudentVO {
	// 필드
	private String stdNo; // std_no
	private String stdName; // std_name
	private String stdPhone; // std_phone
	private String address; // address
	private String birthDate; // birth_date >> 자바에서도 DATE타입이 있는데
	private Date creationDate; //

	// 생성자

	// 메서드
	// 값을 보기 위해서
	@Override
	public String toString() {
		return "StudentVO [stdNo=" + stdNo + ", stdName=" + stdName + ", stdPhone=" + stdPhone + ", address=" + address
				+ ", birthDate=" + birthDate + ", creationDate=" + creationDate + "]";
	}

	// 간략하게 보여주기 위함
	public String briefShow() {
		return stdNo + "\t" + stdName + "\t\t" + stdPhone;
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdPhone() {
		return stdPhone;
	}

	public void setStdPhone(String stdPhone) {
		this.stdPhone = stdPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
