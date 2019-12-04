package ma.ac.emi.students.programme.teacher;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectTestTest {
    SinginController controll = new SinginController();
    String msg;
    @Test
    public void verifyConnection1() {
        User u = new User("imane", "123");
        msg=controll.Verify(u);
        if(!msg.equals("true")) fail("username ou password incorrect");
    }
    @Test
    public void verifyConnection2() {
        User u = new User("imane", "1");
        msg=controll.Verify(u);
        if(!msg.equals("false")) fail("password incorrect");
    }
    @Test
    public void verifyConnection3() {
        User u = new User("i", "123");
        msg=controll.Verify(u);
        if(!msg.equals("false")) fail("mot de passe ou password incorrect");
    }

    @Test
    public void verifyConnection4() {
        User u = new User("i", "1");
        msg=controll.Verify(u);
        if(!msg.equals("false")) fail("username et password incorrect");
    }
}