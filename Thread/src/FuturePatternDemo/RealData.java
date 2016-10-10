package FuturePatternDemo;

//����ౣ����̵߳Ľ��, ����ʵ��ֻ�������߳��С���Ȼ��client�̻߳��ʵ���л�ȡ�𰸣����ǻ�ȡ�м��װ��һ��FutureData��
//FutureData��ֻҪ�̰߳�ȫ������಻��Ҫ���������Ȳ�����
public class RealData implements Data{

	private final String content;
	
	
	//���̵߳ľ���ִ�У����ڸ���Ĺ��캯����ִ�еģ���������ȷ��final�ĳ�Ա���и�ֵ��֮������final��Ҳ��Ϊ�˰�ȫ���𰸲��ɱ䡣
	public RealData(int count, char c){
		
		//*************Thread to Make Answer***************
		System.out.println("		making RealData(" + count + ", " + c + ") BEGIN");
		char[] buffer = new char[count];
		
		for(int i=0; i<count; i++){
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		System.out.println("		making RealData(" + count + ", " + c + ") END");
		//*************************************************
		
		this.content = new String(buffer);
	}
	
	@Override
	public String getContent() {
		return content;
	}

}
