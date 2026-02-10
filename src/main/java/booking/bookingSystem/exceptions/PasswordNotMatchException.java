/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package booking.bookingSystem.exceptions;

/**
 *
 * @author Ruggery
 */
public class PasswordNotMatchException extends RuntimeException{

    /**
     * Creates a new instance of <code>PasswordNotMatchException</code> without
     * detail message.
     */
    public PasswordNotMatchException() {
    }

    /**
     * Constructs an instance of <code>PasswordNotMatchException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
