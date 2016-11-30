package client;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		Person person = new Person.Builder()
				.setName("lsj")
				.setSex("boy")
				.setAge(23)
				.setFaterName("lhw")
				.setMotherName("hl")
				.setBirthday(new Date())
				.build();
	
		System.out.println(person);
		
		person.Update(new Person.Builder()
				.setName("hjs")
				.setSex("girl")
				.setAge(23)
				.setBirthday(new Date()));
		System.out.println(person);
	}
}
