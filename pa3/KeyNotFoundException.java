/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 2/18/2019
 * runtime exception for key not found
 */

public class KeyNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public KeyNotFoundException()
	{
		super("key not found");
	}
	
	public KeyNotFoundException(String s)
	{
		super(s);
	}
}
