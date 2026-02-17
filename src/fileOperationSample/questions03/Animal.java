package fileOperationSample.questions03;

public class Animal {
	private String name;
	private String kind;
	private int age;
	
	public Animal(
			String name,
			String kind,
			int age) {
		
		this.name = name;
		this.kind = kind;
		this.age = age;
		
	}
	
	public String getName() {
		return this.name;
	}
	public String getKind() {
		return this.kind;
	}
	public int getAge() {
		return this.age;
	}
	
	
}
