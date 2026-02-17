package fileOperationSample.practice.questions04;

public class Animal {

	private String name;
	private String kind;
	private int age;


	public Animal(String name, String kind, int age) {
		super();
		this.name = name;
		this.kind = kind;
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public String getKind() {
		return kind;
	}


	public int getAge() {
		return age;
	}


	@Override
	public String toString() {
		return name +","+ kind + ","+ age ;
	}




}
