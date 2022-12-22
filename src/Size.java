
public enum Size {
	BigSize(20)
	,MiddleSize(12)
	,SmallSize(4);
	
	private int num;
	
	private Size(int num)
	{
		this.num=num;
	}

	public int getNum() {
		return num;
	}
}
