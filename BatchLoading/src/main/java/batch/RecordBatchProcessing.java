package main.java.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import main.java.batch.Job.RecordLoadJob;
@EnableBatchProcessing
@SpringBootApplication
@ComponentScan("main.java, com.global.jdbctemplate")
public class RecordBatchProcessing {

	@Autowired 
	private static SimpleJobLauncher jobLauncher;
	@Autowired
	private static RecordLoadJob recordLoadJob;
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(RecordBatchProcessing.class);
		//We dont want this starting out as a web application. 
		app.setWebApplicationType(WebApplicationType.NONE);
		
		//Executes the application. 
		ConfigurableApplicationContext ctx = app.run(args);
		
		try {
			jobLauncher.run(recordLoadJob, new JobParameters());
			
		} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
		

	}
}