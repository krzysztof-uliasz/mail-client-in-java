import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class Mail {
    private final String recipient;
    private final String subject;
    private final String text;

    public Mail(String recipient, String subject, String text) {

        this.recipient = recipient;
        this.subject = subject;
        this.text = text;

        Email email = EmailBuilder
                .startingBlank()
                .from("email@example.com")
                .to(recipient)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "", "")
                .buildMailer() ) {

            mailer.sendMail(email);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getRecipient() {
        return recipient;
    }
    public String getSubject() {
        return subject;
    }
    public String getText() {
        return text;
    }
}
