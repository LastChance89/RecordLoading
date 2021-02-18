package main.java.batch;

import org.springframework.batch.item.ItemProcessor;

import main.java.model.Record;

public class Processor implements ItemProcessor<Record, Record>{

	@Override
	public Record process(Record record) throws Exception {
		//Set a blank note so we dont get null pointer exception in the insert phase. 
		if(record.getNote() == null) {
			record.setNote("");
		}
		return record;
		
	}

}
