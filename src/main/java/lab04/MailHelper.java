package lab04;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author balikm1
 */
public class MailHelper {

    private Mail mail;

    public Mail getMail() {
        return mail;
    }

    public MailHelper() {
        mail = new Mail();
    }

    public void createAndSendMail(String to, String subject, String body)
    {
        getMail(to, subject, body);
        saveMail();
        handleDebugAndSend();
    }

    private void handleDebugAndSend() {
        if (!Configuration.isDebug) {
            (new Thread(() -> {
                sendMail(mail.getMailId());
            })).start();
        }
    }

    private void saveMail() {
        DBManager dbManager = new DBManager();
        dbManager.saveMail(mail);
    }

    private void getMail(String to, String subject, String body) {
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setBody(body);
        mail.setIsSent(false);
    }

    public void sendMail(int mailId)
    {
        try
        {
            // get entity
            Mail mail = new DBManager().findMail(mailId);
            if (mail == null) {
                return;
            }

            if (mail.isSent()) {
                return;
            }

            String from = "user@fel.cvut.cz";
            String smtpHostServer = "smtp.cvut.cz";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);
            Session session = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo(), false));
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody(), "UTF-8");

            // send
            Transport.send(message);
            mail.setIsSent(true);
            new DBManager().saveMail(mail);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    
}
