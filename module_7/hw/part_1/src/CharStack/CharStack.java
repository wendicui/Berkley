package CharStack;
import java.io.*;

public class CharStack {
    private char[] m_data;

    private int m_ptr;

    public CharStack(int size)
    {
        m_ptr = 0;
        m_data = new char[(size > 1 ? size : 10)];
    }

    public void push(char c)
    {
        if (m_ptr >= m_data.length)
        {
            // Grow the array automatically
            char[] tmp =
                    new char[m_data.length * 2];

            System.arraycopy(m_data, 0,
                    tmp, 0,
                    m_data.length);
            m_data = tmp;
        }
        m_data[m_ptr++] = c;
    }

    public char pop()
            throws UnderflowException
    {
        if(m_ptr == 0 )
            throw new UnderflowException();
        else{return m_data[--m_ptr];}
    }


}



//create exception class
class UnderflowException extends Exception{
    private String msg = "Your Stack is Empty! No more POPPING!";
    public UnderflowException(){};
    public String getMsg(){return msg;}
}
