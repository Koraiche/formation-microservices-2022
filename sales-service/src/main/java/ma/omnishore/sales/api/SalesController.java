package ma.omnishore.sales.api;

import java.util.List;

import ma.omnishore.sales.dto.ProductCodesDto;
import ma.omnishore.sales.dto.SupplierDto;
import ma.omnishore.sales.feign.wrapper.ProductClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.omnishore.sales.domain.Sale;
import ma.omnishore.sales.service.ISalesService;

/**
 * Sale controller.
 **/
@RestController
@RequestMapping("/api/sale")
public class SalesController {

	@Autowired
	private ISalesService salesService;

	@Autowired
	private ProductClientWrapper productClientWrapper;
	private static final Logger log = LoggerFactory.getLogger(SalesController.class);

	// -------------------get All Sales-------------------------------------------
	@GetMapping
	public ResponseEntity<List<Sale>> findAll() {
		log.info("Returning sale list from database.");
		List<Sale> sales = salesService.getAllSales();
		return new ResponseEntity<>(sales, HttpStatus.OK);
	}

	// -------------------Retrieve Single Sale------------------------------------
	@GetMapping(value = "/{id}")
	public ResponseEntity<Sale> getSale(@PathVariable("id") long id) {
		Sale sale = salesService.getSale(id);
		return new ResponseEntity<>(sale, HttpStatus.OK);
	}
	
	// -------------------Retrieve Sales By ClientId------------------------------------
		@GetMapping(value = "/client/{id}")
		public ResponseEntity<List<Sale>> getSalesByClient(@PathVariable("id") long id) throws InterruptedException {
			List<Sale> sales = salesService.getSalesByClient((id));
			return new ResponseEntity<>(sales, HttpStatus.OK);
		}

	@GetMapping(value = "/client/{id}/suppliers")
	public ResponseEntity<List<String>> getSuppliersByClient(@PathVariable("id") long id) throws InterruptedException {
		List<String> productCodeList = salesService.getProductsCodeByClientId(id);
		List<String> suppliers = productClientWrapper.getSuppliers(productCodeList);
		return new ResponseEntity<>(suppliers,HttpStatus.OK);
	}

	// -------------------Create a Sale-------------------------------------------
	@PostMapping
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		sale = salesService.createSale(sale);
		return new ResponseEntity<>(sale, HttpStatus.CREATED);
	}

	// ------------------- Delete a Sale-----------------------------------------
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Sale> deleteSale(@PathVariable("id") long id) {
		salesService.deleteSale(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
