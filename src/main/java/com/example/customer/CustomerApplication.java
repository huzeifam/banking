package com.example.customer;

import com.example.customer.model.AllTimeCustomers;
import com.example.customer.model.CustomerResponse;
import com.example.customer.repository.AllTimeCustomersRepository;
import com.example.customer.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@SpringBootApplication
public class CustomerApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CustomerApplication.class, args);

		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
		AllTimeCustomersRepository allTimeCustomersRepository = applicationContext.getBean(AllTimeCustomersRepository.class);

		CustomerResponse customer1 = new CustomerResponse();
		customer1.setCustomerNo(123456789);
		customer1.setIdCardNo("T22000129");
		customer1.setBirthDate(LocalDate.parse("1990-01-01"));
		customer1.setFirstName("Erika");
		customer1.setLastName("Mustermann");
		customer1.setSex("Female");
		customer1.setEmail("erika.mustermann@email.de");
		customer1.setTelephone("+49176123456345");
		customer1.setStreet("Marktplatz");
		customer1.setStreetNo("1");
		customer1.setZipCode("93052");
		customer1.setCity("Regensburg");
		customer1.setCountry("Deutschland");
		customer1.setHasOnlineBanking(true);
		customer1.setInvesting(false);
		customer1.setNaturalPerson(true);
		customer1.setHasAnotherBank(false);
		customer1.setSaving(true);
		customer1.setCreditWorthy(true);

		AllTimeCustomers archiveCustomer1 = new AllTimeCustomers();
		archiveCustomer1.setCustomerNo(123456789);
		archiveCustomer1.setIdCardNo("T22000129");
		archiveCustomer1.setBirthDate(LocalDate.parse("1990-01-01"));
		archiveCustomer1.setFirstName("Erika");
		archiveCustomer1.setLastName("Mustermann");
		archiveCustomer1.setSex("Female");
		archiveCustomer1.setEmail("erika.mustermann@email.de");
		archiveCustomer1.setTelephone("+49176123456345");
		archiveCustomer1.setStreet("Marktplatz");
		archiveCustomer1.setStreetNo("1");
		archiveCustomer1.setZipCode("93052");
		archiveCustomer1.setCity("Regensburg");
		archiveCustomer1.setCountry("Deutschland");
		archiveCustomer1.setHasOnlineBanking(true);
		archiveCustomer1.setInvesting(false);
		archiveCustomer1.setNaturalPerson(true);
		archiveCustomer1.setHasAnotherBank(false);
		archiveCustomer1.setSaving(true);
		archiveCustomer1.setCreditWorthy(true);

		CustomerResponse customer2 = new CustomerResponse();
		customer2.setCustomerNo(987654321);
		customer2.setIdCardNo("TTTT00001");
		customer2.setBirthDate(LocalDate.parse("1982-01-05"));
		customer2.setFirstName("Max");
		customer2.setLastName("Mustermann");
		customer2.setSex("Male");
		customer2.setEmail("max.mustermann@email.de");
		customer2.setTelephone("+49176123456789");
		customer2.setStreet("Hauptstraße");
		customer2.setStreetNo("11");
		customer2.setZipCode("10411");
		customer2.setCity("Berlin");
		customer2.setCountry("Deutschland");
		customer2.setHasOnlineBanking(true);
		customer2.setInvesting(true);
		customer2.setNaturalPerson(true);
		customer2.setHasAnotherBank(false);
		customer2.setSaving(true);
		customer2.setCreditWorthy(true);

		AllTimeCustomers archiveCustomer2 = new AllTimeCustomers();
		archiveCustomer2.setCustomerNo(987654321);
		archiveCustomer2.setIdCardNo("TTTT00001");
		archiveCustomer2.setBirthDate(LocalDate.parse("1982-01-05"));
		archiveCustomer2.setFirstName("Max");
		archiveCustomer2.setLastName("Mustermann");
		archiveCustomer2.setSex("Male");
		archiveCustomer2.setEmail("max.mustermann@email.de");
		archiveCustomer2.setTelephone("+49176123456789");
		archiveCustomer2.setStreet("Hauptstraße");
		archiveCustomer2.setStreetNo("11");
		archiveCustomer2.setZipCode("10411");
		archiveCustomer2.setCity("Berlin");
		archiveCustomer2.setCountry("Deutschland");
		archiveCustomer2.setHasOnlineBanking(true);
		archiveCustomer2.setInvesting(true);
		archiveCustomer2.setNaturalPerson(true);
		archiveCustomer2.setHasAnotherBank(false);
		archiveCustomer2.setSaving(true);
		archiveCustomer2.setCreditWorthy(true);


		customerRepository.save(customer1);
		customerRepository.save(customer2);

		allTimeCustomersRepository.save(archiveCustomer1);
		allTimeCustomersRepository.save(archiveCustomer2);
	}




	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
