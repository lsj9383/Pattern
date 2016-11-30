#生成器模式(Builder Pattern)
若一个对象的方法中具备了相当多的参数，那么在调用该方法时写入参数将会致人迷惑。如有一个**Person类**，其构造器方法具有相当多的参数:
```java
public class Person {
	final private String name;
	final private int age;
	final private String sex;
	final private String FaterName;
	final private String MotherName;
	final private String LoverName;
	final private String ChildName;
	final private Date birthday;
	
	public static class Builder{
		private String name;
		private int age;
		private String sex;
		private String FaterName;
		private String MotherName;
		private String LoverName;
		private String ChildName;
		private Date birthday;
	
	public Person(String name, int age, Strin sex, String FaterName, String MotherName, String LoverName, String ChildName, Date birthday){
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.FaterName = FaterName;
		this.MotherName = MotherName;
		this.LoverName = LoverName;
		this.ChildName = ChildName;
		this.birthday = birthday;
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
```
那么在创建一个新对象时，将会写出一个可读性极差的代码：
```java
Person person = new Person("lsj", 23, "boy", "lhw", "hl", null, null, new Date());
```
虽然在C#已经克服了该问题，但是在Java中该问题仍然存在，但是我们可以通过Builder Pattern以克服该问题。
[这里]()展示的生成器模式在Java的一个应用。

##解决
核心的方案是通过将参数的构建由一个Builder对象来完成，Builder对象实质上是对参数进行保存。只有在我们参数构建完成的时候，才进行真正的对象实例化。
```java
public class Person{
	...
	
	public static class Builder{
		private String name;
		private int age;
		private String sex;
		private String FaterName;
		private String MotherName;
		private String LoverName;
		private String ChildName;
		private Date birthday;
		
		setter method ...
		
		public Person build(){
			return new Person(this);
		}
	}
	
	public Person(Builder builder){
		...
	}
}
```
客户端实例化:
```java
Person person = new Person.Builder()
							.setName("lsj")
							.setSex("boy")
							.setAge(23)
							.setFaterName("lhw")
							.setMotherName("hl")
							.setBirthday(new Date())
							.build();
```