# MVC(Model-View-Controller)

模型-视图-控制器模式(Model-View-Controller, MVC)是一个将视图页面，控制逻辑和业务数据进行解耦的设计模式，由于该设计模式中涉及到了多种涉及模式，所以这是一个复合型涉及模式。主要涉及的设计模式有：
* 策略模式，View可以切换显示方式，Controller可以切换对View的具体的响应方式，这都依赖于策略模式。
* 观察者模式，View是一个观察者，观察Model的数据情况。
* 组合模式，View中可以有子View，以此形成树形的组合模式。

## MVC之间的关系
* 视图中的点击事件会将处理业务委托给控制器
* 控制器根据视图发送过来的请求，可以修改模型数据，也可以直接操作视图。
* 模型数据修改以后将会通知监听该模型的视图，并且提供了一系列的接口以便视图获取数据。

## MVC结构
为了更好的说明MVC结构之间的关系，以下是创建一个具有黑夜风格的目录页面的MVC
### Model
模型可以用接口的形式提供，再编写实现类，也可以直接给出具体的类。用接口更具灵活性，但是通常业务不需要那么有灵活性的Model。一个模型也可能有很多种类型的观察者，Model中不同类型的数据修改会通知不同的观察者。
```Java
public class ListModel{
	String title;
	ArrayList<String> entry;
	ArrayList<ObserverType1> observersGroup1 = new ArrayList<>();
	ArrayList<ObserverType2> observersGroup2 = new ArrayList<>();
	
	...	
	
	// 提供给View和Controller以获取Model的内容
	pulic String getTitle(){
		return title;
	}
	
	public String getEntry(int idx){
		return entry.get(idx);
	}
	
	// 主要用Controller来对模型数据进行更新, 更新将会触发对Model的通知。
	public void setTitle(String title){
		this.title = title;
		notifyObserverGroup1(new Event(SET_TITLE, "设置标题"));
	}
	
	// 周期性的通知
	public void periodUpdate(){
		notifyObserverGroup1();
	}
	
	private void notifyObserverGroup1(Event e){
		for(ObserverType1 observer : observersGroup1){
			observer->notify(this, e);
		}
	}
	
	// 注册和注销观察者
	void register(IndexView view){
		observersGroup1->add(view);
	}
	
	void removeObserver(IndexView view){
		observersGroup1.remove(view);
	}
	...
}
```


### View
 每种页面都提供了一个页面接口，如目录页面的接口应该为`IndexView`, 主页页面的接口应该为`HomepageView`。这明确了`控制器`可以直接控制视图哪些数据，控制器通过执行这些方法，视图进行相应的渲染。需要注意的是，这不需要提供视图所有的数据，因为很多数据并非交给控制器来控制，而是由模型的数据更新进而通知视图，视图自行对模型的数据进行更新渲染。
```Java
public interface IndexView{
	void setButtonEnable();
	void setButtonDisable();
	...
}
```
在实现类中需要实现在set方法调用以后，如何将对应的数据渲染到页面中。除此之外，最重要的，实现类还需要继承观察者接口，这样才能获得来自Model的通知。如下实现一个具有黑夜风格的主题页面类：
```Java
public class DarkIndexView implements HomepageView, ObserverType1{
	IndexController controller;
	public DarkIndexView(IndexController controller, ListModel model){
		 this.controller = controller;
		 model.addObserver(this);
	}

	void setButtonEnable(){
		...
	}
	
	void setButtonDisable(){
		...
	}
	
	// 视图获得模型的更新通知
	void notify(Model o, Event e){
		...
	}
	
	// 视图事件委托给控制器来执行, 比如这是一个按钮单机事件
	static int cnt = 0;
	void actionClickButton(ActionEvent event){
		if((cnt++)%2==0){
			controller.setTitle("黑暗风目录");
		}else{
			controller.setTitle("还是黑暗风目录");
		}
	}
}
```
需要注意的是，在视图进行初始化的时候需要传入控制器以将视图事件委托给控制器，并且也需要传入视图并将自己设置为视图的观察者。

### Controller
视图中的各类事件触发后，由视图获取到，并根据具体情况向控制器发送请求。
```Java
public interface Controller{
	void setTitle(String title);
}
```
控制器接口决定了Contrller可以接受哪些请求，Controller的实现类将会决定对这些请求做什么处理，这些处理可能会导致模型更新，也可能导致视图修改。
```Java
public class IndexController implements Controller{
	ListModel model;
	IndexView view;
	
	public IndexController(ListModel model){
		this.model = model;
		this.view = new DarkIndexView(this, model);
	}
	
	public void setTitle(String title){
		model.setTitle(title);
	}
}
```

### 初始化
需要修改View主题的时候，就在控制器代码中修改具体new什么风格的View。
```Java
ListModel model = new ListModel();
IndexController controller = new IndexController(model);
```
需要修改控制器的时候就在main函数中修改具体new那个控制器即可。