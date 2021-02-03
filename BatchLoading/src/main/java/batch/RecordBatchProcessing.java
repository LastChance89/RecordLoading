package main.java.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@EnableBatchProcessing
@SpringBootApplication
public class RecordBatchProcessing {
	
	@Autowired 
	private static SimpleJobLauncher jobLauncher;
	
	public static void main(String[] args) {
		//SpringApplication.run(RecordBatchProcessing.class, args);
		
		SpringApplication app = new SpringApplication(RecordBatchProcessing.class);
		//We dont want this starting out as a web application. 
		app.setWebApplicationType(WebApplicationType.NONE);
		
		//Executes the application. 
		ConfigurableApplicationContext ctx = app.run(args);

		//		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
		//.toJobParameters();
		
		//JobLauncher job = new JobLauncher(JobLauncher.class);
		//Job job =  ctx.getBean("csvFileTODatabaseJob",Job.class);
		/*
		try {
			 jobLauncher.run(job,param);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}