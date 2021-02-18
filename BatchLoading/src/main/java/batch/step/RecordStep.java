package main.java.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import main.java.batch.Processor;
import main.java.model.Record;


@Component
public class RecordStep {
	@Autowired
	public StepBuilderFactory stepBuilderFactory; 
	@Autowired
	private ApplicationContext context;
	
	@SuppressWarnings("unchecked")
	public Step csvFileToDatabaseStep() {
		return stepBuilderFactory.get("csvFileToDatabaseStep").<Record, Record>chunk(500)
				.reader((ItemReader<Record>) context.getBean("csvRecordReader")).processor(new Processor())
				.writer((ItemWriter<Record>) context.getBean("recordWriter")).build();
	}
}
