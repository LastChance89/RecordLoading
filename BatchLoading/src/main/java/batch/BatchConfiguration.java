package main.java.batch;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.DefaultExecutionContextSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import main.java.model.Record;

@Configuration
//@EnableBatchProcessing Only really need this is if we want an out of the box solution. 
public class BatchConfiguration implements BatchConfigurer  {

	
	@Autowired
	private JdbcTemplate template;

	/*TODO: Fix me. Worked fine for the inital batch loading 
	 * but is out of place here. 
	 * Move somewhere else. 
	 * 
	 */
	@Bean
	ItemProcessor<Record, Record> recordProcessor() {
		return new Processor();
	}
	
	
	@Override
	public JobRepository getJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setJdbcOperations(template);
		factory.setTransactionManager(getTransactionManager());
		return factory.getObject();
	}

	@Override
	public PlatformTransactionManager getTransactionManager() throws Exception {
		JdbcTransactionManager manager = new JdbcTransactionManager();
		manager.setDataSource(template.getDataSource());
		return manager;
	}

	@Override
	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor("BatchLoadingRecords"));
		return jobLauncher;
	}

	@Override
	public JobExplorer getJobExplorer() throws Exception {
		JobExplorerFactoryBean factory = new JobExplorerFactoryBean(); 
		factory.setJdbcOperations(template);
		factory.setSerializer(new DefaultExecutionContextSerializer());
		return factory.getObject();
	}

}
