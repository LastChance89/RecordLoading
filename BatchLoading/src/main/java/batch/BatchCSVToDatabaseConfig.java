package main.java.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.java.batch.listner.BatchJobCompleteListener;
import main.java.batch.step.RecordStep;
import main.java.model.Record;

@Configuration
@EnableBatchProcessing
public class BatchCSVToDatabaseConfig {

	
	/*
	@Autowired 
	public BatchCSVToDatabaseConfig(JobBuilderFactory jobBuilderFactory,RecordStep recordStep) {
		this.recordStep = recordStep; 
		this.jobBuilderFactory = jobBuilderFactory;
	}
	*/
	@Bean
	ItemProcessor<Record, Record> recordProcessor() {
		return new Processor();
	}

	/*
	@Bean
	public Job csvFileToDatabaseJob(BatchJobCompleteListener listener) {
		return jobBuilderFactory.get("csvFileToDatabaseJob").
				incrementer(new RunIdIncrementer()).listener(listener)
				.flow(recordStep.csvFileToDatabaseStep()).end().build();
	}
*/
}
