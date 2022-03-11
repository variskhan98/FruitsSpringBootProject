# FruitsSpringBootProject

This project required me to create an application that uses CRUD functionality whilst incorporating all the topics taught.

Project Management Tools - Jira & Github
Databases - mySQL and H2 
Backend Application - SpringBoot Java
Front End - HTML, CSS & JAVASCRIPT
Testing - JUnit (Backend), Postman(Frontend)

I used Jira in order to form a Kanban board creating user stories and epics detailing issues linked with the project giving me a good structure to follow. The user stories is shown below:

![Screenshot (2)](https://user-images.githubusercontent.com/98025285/157782554-6fabfcdc-24d5-4abb-a276-6055fa806f9d.png)


Front-End:
Using Java in a Spring Boot Framework I created the back-end of the project. I created a FruitsController class and methods were added for CRUD functionality

	
	
    private FruitsService service;
    
    @Autowired
    public FruitsController(FruitsService service) {
    	super();
    	this.service=service;
    	
    }

	
	
	
	
	@PostMapping("/create")
	public ResponseEntity<Fruits> createFruits(@RequestBody Fruits f) 
	{
		Fruits created = this.service.createFruits(f);
		ResponseEntity<Fruits> response = new ResponseEntity<Fruits>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Fruits>> getAllfru() {
		return ResponseEntity.ok(this.service.getAllFru());
		
	}
	
	@GetMapping("/get/{id}")
	public Fruits getFruits(@PathVariable Integer id) {
		return this.service.getFru(id);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Fruits> replaceFruits(@PathVariable Integer id, @RequestBody Fruits newFruits) {
		Fruits body = this.service.replaceFruits(id, newFruits);
		ResponseEntity<Fruits> response = new ResponseEntity<Fruits>(body, HttpStatus.ACCEPTED);
		return response;
		
		}
	
	@DeleteMapping("/remove/{id}") 
	public ResponseEntity<?> removeFruits(@PathVariable Integer id) {
		this.service.removeFruits(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		
	
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Fruits>> getFruitsByName(@PathVariable String name) {
	List<Fruits> found = this.service.getFruByName(name);
	return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getbyQuantity/{quantity}")
	public ResponseEntity<List<Fruits>> getFruitsByQuantity(@PathVariable Integer quantity) {
		List<Fruits> found = this.service.getAllFruByQuantity(quantity);
		return ResponseEntity.ok(found);
	
	}
	
	@GetMapping("/getbySeed/{seed}")
	public ResponseEntity<List<Fruits>> getFruitsBySeed(@PathVariable boolean seed) {
		List<Fruits> found = this.service.getFruBySeed(seed);
		return ResponseEntity.ok(found);
	}
	
	}



Testing:
JUnit testing was used for this project. It returned no failures with a coverage of 87.7%
![testing](https://user-images.githubusercontent.com/98025285/157784945-23f53c3e-c9fa-4efe-b447-4124362fe18a.png)

![coverage](https://user-images.githubusercontent.com/98025285/157784914-354a5175-dc6b-4abc-aa4c-68bb5df44e7b.png)

Front-End:
The front-end is built using HTML, CSS and JavaScript. HTML and CSS were used to create the design of the page.

![front end](https://user-images.githubusercontent.com/98025285/157785156-9bd6e87b-5afb-444c-85b6-6b357df34bd3.png)




