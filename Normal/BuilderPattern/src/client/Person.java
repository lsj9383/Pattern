package client;

import java.util.Date;

public class Person {
	private String name;
	private int age;
	private String sex;
	private String FaterName;
	private String MotherName;
	private String LoverName;
	private String ChildName;
	private Date birthday;
	
	public static class Builder{
		private String name;
		private int age;
		private String sex;
		private String FaterName;
		private String MotherName;
		private String LoverName;
		private String ChildName;
		private Date birthday;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setAge(int age) {
			this.age = age;
			return this;
		}
		public Builder setSex(String sex) {
			this.sex = sex;
			return this;
		}
		public Builder setFaterName(String faterName) {
			FaterName = faterName;
			return this;
		}
		public Builder setMotherName(String motherName) {
			MotherName = motherName;
			return this;
		}
		public Builder setLoverName(String loverName) {
			LoverName = loverName;
			return this;
		}
		public Builder setChildName(String childName) {
			ChildName = childName;
			return this;
		}
		public Builder setBirthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}
		
		public Person build(){
			return new Person(this);
		}
	}
	
	public Person(Builder builder){
		Update(builder);
	}
	
	public void Update(Builder builder){
		name = builder.name;
		age = builder.age;
		sex = builder.sex;
		FaterName = builder.FaterName;
		MotherName = builder.MotherName;
		LoverName = builder.LoverName;
		ChildName = builder.ChildName;
		birthday = builder.birthday;
	}

	@Override
	public String toString() {
		return String.format(
				"name : %s \n"
				+ "age : %s \n"
				+ "sex : %s \n"
				+ "Father : %s \n"
				+ "Mother : %s \n"
				+ "Lover : %s \n"
				+ "Child : %s \n"
				+ "birthday : %s", name, age, sex, FaterName, MotherName, LoverName, ChildName, birthday);
	}
}
