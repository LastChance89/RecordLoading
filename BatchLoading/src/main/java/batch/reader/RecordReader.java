package main.java.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import main.java.batch.mapper.RecordFieldMapper;
import main.java.model.Record;

@Component
public class RecordReader {
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
    	reader.setLinesToSkip(6);// will need to impliment this, also need to still figure out how to read in the key
    	return reader;
    }
}
