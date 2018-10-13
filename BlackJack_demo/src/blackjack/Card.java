package blackjack;

public class Card { // 一张牌
	
	private String faceValue; // 牌的面值
	private int index; // 代表花色
	
	public Card(String faceValue,int index)
	{
		this.faceValue = faceValue;
		this.index = index;
	}

	public String getFaceValue() // 获取牌的面值
	{
		return this.faceValue;
	}
	
	public int getPoint() // 获得牌的点数
	{
		String value = this.faceValue.toUpperCase();
		if(value=="J"||value=="Q"||value=="K")
		{
			return 10;
		}
		else if(value=="A") // 默认让它返回1
		{
			return 1;
		}
		else{
			return Integer.parseInt(value); // 字符串int
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
