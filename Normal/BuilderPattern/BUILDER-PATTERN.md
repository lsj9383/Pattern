#������ģʽ(Builder Pattern)
Ŀǰֻ�Ǽ�����Builder Pattern��һ��Ӧ�á�
[����](https://github.com/lsj9383/Pattern/blob/master/Normal/BuilderPattern/src/client/Main.java)չʾ��������ģʽ��Java��һ��Ӧ�á�

##����
��һ������ķ����о߱����൱��Ĳ�������ô�ڵ��ø÷���ʱд��������������Ի�����һ��**Person��**���乹�������������൱��Ĳ���:
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
��ô�ڴ���һ���¶���ʱ������д��һ���ɶ��Լ���Ĵ��룺
```java
Person person = new Person("lsj", 23, "boy", "lhw", "hl", null, null, new Date());
```
��Ȼ��C#�Ѿ��˷��˸����⣬������Java�и�������Ȼ���ڣ��������ǿ���ͨ��Builder Pattern�Կ˷������⡣

##���
���ĵķ�����ͨ���������Ĺ�����һ��Builder��������ɣ�Builder����ʵ�����ǶԲ������б��档ֻ�������ǲ���������ɵ�ʱ�򣬲Ž��������Ķ���ʵ������
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
�ͻ���ʵ����:
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

##��չ
Builder���������ڹ��췽���Ĳ�����ʼ����������ͨ�����ĳ�ʼ��Ҳ���ʺϵġ�
```java
class MyClass{
	void Method(Builder build){
		...
	}
}
```