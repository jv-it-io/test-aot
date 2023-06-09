package bootiful.aot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class AotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AotApplication.class, args);
	}


	@Bean
	ApplicationRunner applicationRunner(CustomerRespository customerRespository){
		return args -> customerRespository.findByName("test").forEach(System.out::println);
	}


}

@Controller
@ResponseBody
class CustomerController{
	private  final CustomerRespository customerRespository;

	CustomerController(CustomerRespository customerRespository) {
		this.customerRespository = customerRespository;
	}

	@GetMapping("/customers")
	Iterable<Customer> getCustomers(){
		return customerRespository.findAll();
	}

	@GetMapping("/customers/{name}")
	Iterable<Customer> byName(@PathVariable String name){
		return customerRespository.findByName(name);
	}


}

@Repository
interface CustomerRespository extends CrudRepository<Customer, Integer>{

	Iterable<Customer> findByName(String name);
}
record Customer(@Id Integer id, String name){

}
