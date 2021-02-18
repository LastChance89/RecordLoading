package main.java.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import main.java.model.Record;

public class RecordFieldMapper implements FieldSetMapper<Record> {

	@Override
	public Record mapFieldSet(FieldSet fieldSet) throws BindException {
		Record record = new Record();
		record.setType(fieldSet.readString("TYPE"));
		record.setRecordDate(fieldSet.readDate("RECORD_DATE"));
		record.setStartTime(fieldSet.readString("START_TIME"));
		record.setEndTime(fieldSet.readString("END_TIME"));
		record.setPower_usage(fieldSet.readDouble("POWER_USAGE"));
		record.setUnits(fieldSet.readString("UNITS"));
		StringBuilder cost = new StringBuilder(fieldSet.readString("COST"));
		cost.deleteCharAt(cost.indexOf("$"));
		record.setCost(Double.valueOf(cost.toString()));
		record.setNote(fieldSet.readString("NOTES"));
		return record;
	}

}
