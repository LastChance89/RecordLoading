package main.java.batch.writer;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import main.java.model.Record;



@Component
public class RecordItemWriter {
	
	@Autowired
	JdbcTemplate template;
	
	@Bean
	public JdbcBatchItemWriter<Record> recordWriter() {
		JdbcBatchItemWriter<Record> rWriter = new JdbcBatchItemWriter<Record>();
		rWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Record>());
		rWriter.setSql("INSERT INTO RECORDS (TYPE,RECORD_DATE, " + "START_TIME,END_TIME,POWER_USAGE,UNITS,COST,NOTES)"
				+ " VALUES (:type, :recordDate,:startTime,:endTime,:powerUsage, :units, :cost, :note)");
		rWriter.setDataSource(template.getDataSource());

		return rWriter;
	}

}
