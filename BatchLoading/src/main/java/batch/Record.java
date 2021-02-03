package main.java.batch;

import java.util.Date;

//@Entity
//@Table(name = "RECORDS")
public class Record {
	
	//our composite key. 
//	@Id
	private RecordKey recordKey;
	
//	@Column(name="TYPE")
	private String type; 
//	@Column(name = "POWER_USAGE")
	private Double powerUsage;
//	@Column(name="UNITS")
	private String units;
//	@Column(name="COST")
	private Double cost;
//	@Column(name="NOTES")
	private String note;
	
//	@NotNull
//	@Column(name = "account_number")
	public Long accountnumber;
	
//	@NotNull
//	@Column(name = "record_date")
	public Date recordDate; 
	
//	@NotNull
//	@Column(name = "start_time")
	public String startTime; 
	
//	@NotNull
//	@Column(name = "end_time")
	public String endTime;
	
	public Record() {	
	}
	
//" VALUES (:type, :recordDate,:startTime,:endTime,:powerUsage, units, cost, note)");

	public Record(String type, Date recordDate, String startTime, String endTime,Double powerUsage,String units, 
			Double cost,String note) {
		this.type = type; 
		this.recordDate = recordDate;
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.powerUsage = powerUsage;
		this.units = units;
		this.cost = cost;
		this.note = note; 
	}
	public Record(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Double getPower_usage() {
		return powerUsage;
	}
	public void setPower_usage(Double power_usage) {
		this.powerUsage = power_usage;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	public RecordKey getRecordKey() {
		return recordKey;
	}

	public void setRecordKey(RecordKey recordKey) {
		this.recordKey = recordKey;
	}

	public Double getPowerUsage() {
		return powerUsage;
	}

	public void setPowerUsage(Double powerUsage) {
		this.powerUsage = powerUsage;
	}

	public Long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(Long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
