/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package booking.bookingSystem.exceptions;

/**
 *
 * @author Ruggery
 */
public class WeekPasswordException extends RuntimeException{

    /**
     * Creates a new instance of <code>WeekPasswordException</code> without
     * detail message.
     */
    public WeekPasswordException() {
    }

    /**
     * Constructs an instance of <code>WeekPasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WeekPasswordException(String msg) {
        super(msg);
    }
}
