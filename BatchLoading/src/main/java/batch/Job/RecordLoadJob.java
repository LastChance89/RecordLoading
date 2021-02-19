package main.java.batch.Job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.batch.listner.BatchJobCompleteListener;
import main.java.batch.step.RecordStep;

@Component
public class RecordLoadJob{
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private RecordStep recordStep;
	
	public Job csvFileToDatabaseJob() {
		return jobBuilderFactory.get("csvFileToDatabaseJob").
				incrementer(new RunIdIncrementer())
				.listener(new BatchJobCompleteListener())
				.flow(recordStep.csvFileToDatabaseStep())
				.end()
				.build();
	}

}
