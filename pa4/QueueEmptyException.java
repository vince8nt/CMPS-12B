/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 3/4/2019
 * a runtime exception for Queue
 */

public class QueueEmptyException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public QueueEmptyException(String s)
	{
		super(s);
	}
}
