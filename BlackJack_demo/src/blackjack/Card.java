package blackjack;

public class Card { // һ����
	
	private String faceValue; // �Ƶ���ֵ
	private int index; // ����ɫ
	
	public Card(String faceValue,int index)
	{
		this.faceValue = faceValue;
		this.index = index;
	}

	public String getFaceValue() // ��ȡ�Ƶ���ֵ
	{
		return this.faceValue;
	}
	
	public int getPoint() // ����Ƶĵ���
	{
		String value = this.faceValue.toUpperCase();
		if(value=="J"||value=="Q"||value=="K")
		{
			return 10;
		}
		else if(value=="A") // Ĭ����������1
		{
			return 1;
		}
		else{
			return Integer.parseInt(value); // �ַ���int
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
