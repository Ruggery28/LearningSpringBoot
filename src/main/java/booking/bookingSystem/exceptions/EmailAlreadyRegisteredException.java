/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package booking.bookingSystem.exceptions;

/**
 *
 * @author Ruggery
 */
public class EmailAlreadyRegisteredException extends RuntimeException{

    /**
     * Creates a new instance of <code>EmailAlreadyRegisteredException</code>
     * without detail message.
     */
    public EmailAlreadyRegisteredException() {
    }

    /**
     * Constructs an instance of <code>EmailAlreadyRegisteredException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
