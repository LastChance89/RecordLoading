package main.java.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BatchCSVToDatabaseConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory StepBuilderFactory;
	@Autowired
	private JdbcTemplate template;

    
    @Bean
    public FlatFileItemReader<Record> csvRecordReader(){
    	FlatFileItemReader<Record> reader = new FlatFileItemReader<Record>();
    	reader.setResource(new ClassPathResource("input.csv"));
    	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    	//This sets the fieldSet metadata the fieldSetMapper reads. 
    	//We will change this into metadata. 
    	tokenizer.setNames("TYPE","RECORD_DATE","START_TIME","END_TIME","POWER_USAGE","UNITS","COST","NOTES");
    
    	var mapper = new DefaultLineMapper<Record>();
    	mapper.setLineTokenizer(tokenizer);
    	mapper.setFieldSetMapper(new RecordFieldMapper());
    	reader.setLineMapper(mapper);
    	//reader.setLinesToSkip(6); will need to impliment this, also need to still figure out how to read in the key
    	return reader;
    }
   @Bean 
   ItemProcessor<Record,Record> recordProcessor(){
	   return new Processor();
   }
	
   //Change this to Hibernate. 
   @Bean 
   public JdbcBatchItemWriter<Record> recordWriter(){
	   JdbcBatchItemWriter<Record> rWriter = new JdbcBatchItemWriter<Record>();
	   rWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Record>());
	   rWriter.setSql("INSERT INTO RECORDS (TYPE,RECORD_DATE, "
	   		+ "START_TIME,END_TIME,POWER_USAGE,UNITS,COST,NOTES)"
	   		+ " VALUES (:type, :recordDate,:startTime,:endTime,:powerUsage, :units, :cost, :note)");
	   rWriter.setDataSource(template.getDataSource());
	   
	   return rWriter;
   }
   
   @Bean
   public Step csvFileToDatabaseStep() {
	   return StepBuilderFactory.get("csvFileToDatabaseStep")
			   .<Record, Record>chunk(500)
			   .reader(csvRecordReader())
			   .processor(recordProcessor())
			   .writer(recordWriter())
			   .build();
			   
   }
   
   @Bean
   public Job csvFileTODatabaseJob(BatchJobCompleteListener listener) {
	   return jobBuilderFactory.get("csvFileTODatabaseJob")
			   .incrementer(new RunIdIncrementer())
			   .listener(listener)
			   .flow(csvFileToDatabaseStep())
			   .end()
			   .build();
   }
      
   
}
