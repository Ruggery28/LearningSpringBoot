/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package booking.bookingSystem.exceptions;

/**
 *
 * @author Ruggery
 */
public class UnderAgeUserException extends RuntimeException{

    /**
     * Creates a new instance of <code>UnderAgeUserException</code> without
     * detail message.
     */
    public UnderAgeUserException() {
    }

    /**
     * Constructs an instance of <code>UnderAgeUserException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnderAgeUserException(String msg){
        super(msg);
    }
}
