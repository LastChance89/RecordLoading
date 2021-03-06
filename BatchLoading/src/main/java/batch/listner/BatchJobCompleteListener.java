package main.java.batch.listner;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class BatchJobCompleteListener extends JobExecutionListenerSupport  {

	public BatchJobCompleteListener() {
		
	}
	
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("JOB COMPLETED!");
		}
	}
	
	
}
