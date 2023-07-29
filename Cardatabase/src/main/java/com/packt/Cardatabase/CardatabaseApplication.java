package com.packt.Cardatabase;

import com.packt.Cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {


	@Autowired
	private CarRepository repository;

	@Autowired
	private OwnerRepository ownerRepository;


	@Autowired
	UserRepositoty urepositoty;

	@Override
	public void run(String... args) throws Exception {
		Owner Owner1=new Owner("john","jognson");
		Owner Owner2=new Owner("Mary","Robinson");
		ownerRepository.saveAll(Arrays.asList(Owner1,Owner2));
		Car car1=new Car("Ford","Mustang","Red","ADF-1121",2021,59000,Owner1);
		Car car2= new Car("Nissan","Leaf","White","SSJ-3002",2019,29000,Owner2);
		Car car3=new Car("Toyota","Prius","Silver","KKO-0212",2020,39000,Owner2);
	    repository.saveAll(Arrays.asList(car1,car2,car3));

		urepositoty.save(new User("user","$2y$04$apadytRlw1zho36Eyn8ZCuVMe4Rf1dlRmviu2Pdscbe8yITIXJURq","USER"));
		urepositoty.save(new User("admin","$2y$04$zJsTtKHLB5KFStGyHL39mu3BoRVAaQe5A3GkqEzTf/BqXQ6.u4cTO","ADMIN"));

	}
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}
}
