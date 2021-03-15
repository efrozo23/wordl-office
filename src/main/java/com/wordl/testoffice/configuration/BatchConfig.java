package com.wordl.testoffice.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.wordl.testoffice.component.JobCompletionNotificationListener;
import com.wordl.testoffice.exceptions.MyException;
import com.wordl.testoffice.model.Inventario;
import com.wordl.testoffice.processor.InventarioItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Value("${file.input}")
	private String fileInput;
	
	@Bean
	public FlatFileItemReader reader() {
		
		FlatFileItemReader flatFile = new FlatFileItemReaderBuilder().name("inventarioItemReader")
				.resource(new ClassPathResource(fileInput))
				.delimited()
				.names(new String[] {"nombre","marca","precio","cant_stock","estado","porcentaje_desc"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper() {{
					setTargetType(Inventario.class);
				}})
				.build();
		flatFile.setLinesToSkip(1);
		return flatFile;
				
	}
	
	@Bean
	public JdbcBatchItemWriter writer (DataSource dataSource){
		
	
		
		
		return new  JdbcBatchItemWriterBuilder()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO INVENTARIO (nombre, marca, precio,cant_stock,estado, porcentaje_desc) "
						+ "VALUES (:nombre, :marca, :precio,:cantStock,:estado,:porcentajeDesc)")
				.dataSource(dataSource)
				.build();
		
	}
	
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      
	      .flow(step1)
	      
	      .end()
	      .build();
	}
	

	@Bean
	public Step step1(JdbcBatchItemWriter writer) {
		
		
		return stepBuilderFactory.get("step1")
				.<Inventario, Inventario> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)		
				.build();
	}
	
	@Bean
	public InventarioItemProcessor processor() {
		return new InventarioItemProcessor();
	}

	
	
	
}
