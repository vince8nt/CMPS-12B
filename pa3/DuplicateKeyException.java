/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 2/18/2019
 * runtime exception for duplicate keys
 */

public class DuplicateKeyException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public DuplicateKeyException()
	{
		super("duplicate keys");
	}
	
	public DuplicateKeyException(String s)
	{
		super(s);
	}
}
