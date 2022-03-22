package lab04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailHelperTests {

    public Mail mail;
    String to = "To";
    String subject = "Subject";
    String body = "Body";

    @BeforeEach
    public void setMailHelper() {
        MailHelper mailHelper = new MailHelper();
        mailHelper.createAndSendMail(to, subject, body);
        mail = mailHelper.getMail();
    }

    @Test
    public void setTo_toIsSet_returnsTo() {
        Assertions.assertEquals(to, mail.getTo());
    }

    @Test
    public void setSubject_SubjectIsSet_returnsSubject() {
        Assertions.assertEquals(subject, mail.getSubject());
    }

    @Test
    public void setBody_BodyIsSet_returnsBody() {
        Assertions.assertEquals(body, mail.getBody());
    }
}
