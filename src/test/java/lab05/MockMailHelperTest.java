package lab05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MockMailHelperTest {

    DBManager mockDBManager = Mockito.mock(DBManager.class);
    MailHelper mailHelper = new MailHelper(mockDBManager);

    @Test
    public void negativeVerifyTwoTimes() {
        //mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockDBManager, times(2)).findMail(Mockito.anyInt());
    }

    @Test
    public void positiveVerifySendMailOnce() {
        mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockDBManager).findMail(Mockito.anyInt());
    }

    @Test
    public void mockTest() {
        int mailId = 1;
        Mail mail = getMail();
        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(mail);
        mailHelper.sendMail(mailId);
        //check that findMail was called 1 time
        Mockito.verify(mockDBManager).findMail(mailId);
    }

    @Test
    public void checkMailReturnsCorrectTo() {
        int mailId = Mockito.anyInt();
        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(getMail());
        mailHelper.sendMail(mailId);
        Assertions.assertEquals("ABCD", mailHelper.getMail().getTo());
    }

    private Mail getMail() {
        Mail mail = new Mail();
        mail.setTo("ABCD");
        mail.setBody("BODY");
        mail.setSubject("SUBJECT");
        return mail;
    }
}
