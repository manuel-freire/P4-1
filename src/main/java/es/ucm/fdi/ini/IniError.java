package main.ini;

/**
 * IniError class.
 *
 * @author Samir Genaim genaim@gmail.com
 *
 */
public class IniError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * Class constructor.
	 * @param msg
	 */
	IniError(String msg) {
		super(msg);
	}
}
