#单例模式
单例模式用于创建唯一的对象，其形式相当简单。但其应用分了单线程和多线程两种。
[这里](https://github.com/lsj9383/Pattern/blob/master/Normal/Singleton/Singleton/Program.cs)有个简单的单例C#实现

##一、普通单例模式
```C#
class Unique
{
	private static Unique Instance = null;

	private Unique() { }
	
	public static Unique GetInstance() 
	{
		if (Instance == null)
		{
			Instance = new Unique();
		}

		return Instance;
	}
}
```
从形式上来说，应用单例的对象是将构造函数私有化，以禁止用户反复new出新对象。得到对象的方式是编写一个静态函数，用户通过这个函数可以获得单例对象。程序员就是对静态函数进行修改，以使用户初始得到对象的时候会进行初始化，而后直接得到该对象。

##二、多线程下的单例模式
若在多线程的运行环境下，继续使用传统的方式，是有可能出现两个对象的，这样违反了单例模式。比如两个线程同时在执行`Unique.GetInstance()`以获得单例，则可能存在以下顺序：
```
			线程1					线程2
	  if(Instance==null)
								f(Instance==null)
	Instance = new Unique();
		return Instance;	
								Instance = new Unique();
									return Instance;
```
若存在这样的执行顺序，那么线程2结束后，得到的其实是和线程1有别的新对象。
之所以会发送这种情况，是因为存在竞争，需要引入同步机制。
```C#
class MultiThreadUnique
{
	private static MultiThreadUnique Instance = null;
	private static object root = new oject();
	
	private MultiThreadUnique() { }
	
	public static MultiThreadUnique GetInstance() 
	{
		lock(root)
		{
			if (Instance == null)
			{
				Instance = new Unique();
			}
		}
		return Instance;
	}
}
```
这样子，就可以避免有多个线程会在`if (Instance == null){}`语句块中，以造成生成多个对象。但是由于引入lock的同步机制，势必导致运行效率的降低。若程序员确定该对象每次运行，都是必要的，那么刻意修改为运行效率更高的版本。
```C#
class MultiThreadUnique
{
	private static MultiThreadUnique Instance = new MultiThreadUnique();
	private MultiThreadUnique() { }
	
	public static MultiThreadUnique GetInstance() 
	{
		return Instance;
	}
}
```
在初始化的时候就建立好改对象，若有线程需要则直接返回该对象，不作任何创建。但其实不可能所有单例都必须创建初始化对象，这样太浪费内存了，还存在其他技术提升效率。从这个例子也可以看出，造成之前冲突的时间点，只会在对象刚刚诞生的时候。因此只要在创建新对象的时候进行同步，以后都不进行同步即可。进一步作如下改进：
```C#
class MultiThreadUnique
{
	private static MultiThreadUnique Instance = null;
	private static object root = new object();
	private MultiThreadUnique() { }
	
	public static MultiThreadUnique GetInstance() 
	{
		if (Instance == null)
		{
			lock (root)
			{
				if (Instance == null)
				{
					Instance = new MultiThreadUnique();
				}
			}
		}

		return Instance;
	}
}
```
只需要将`lock`放在`if(Instance==null)`语句块中即可。需要注意的是，这里进行了两次加锁(两次进行null判断)。这是因为可能在执行`Instance = new MultiThreadUnique();`的时候，有现成在`lock`处等待，当它被唤醒时，若不重新进行null判断那么还是会创建新对象。通过这个技术，使得`GetInstance`方法的效率在原基础上提高了不少。[这里](https://github.com/lsj9383/Pattern/blob/master/Normal/Singleton/Singleton/Program.cs)是一个简单的模式示例。