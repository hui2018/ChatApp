package Server;

public class Node {
	protected Node next = null;
	protected String UserName = new String();
	protected String Password = new String();
	
	public Node(String User, String Pass)
	{
		UserName = User;
		Password = Pass;
	}
	public Node Get_next()
	{
		return next;
	}
	public void Set_next(Node SetNext)
	{
		next = SetNext;
	}
}
