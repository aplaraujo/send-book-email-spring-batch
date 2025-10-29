package com.example.send_book_email_spring_batch.processor;

import com.example.send_book_email_spring_batch.domain.UserBookLoan;
import com.example.send_book_email_spring_batch.util.GenerateBookReturnDate;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessLoanNotificationEmailProcessorConfig {
    @Bean
    public ItemProcessor<UserBookLoan, Mail> processLoanNotificationEmailProcessor() {
        return new ItemProcessor<UserBookLoan, Mail>() {
            @Override
            public Mail process(UserBookLoan item) throws Exception {
                Email from = new Email("ananopaisdojavascript@gmail.com", "Biblioteca Municipal");
                Email to = new Email(item.getUser().getEmail());
                Content content = new Content("text/plain", generateEmailText(item));
                Mail mail = new Mail(from, "Notificação de devolução de livro", to, content);
                Thread.sleep(1000);
                return mail;
            }
        };
    }

    private String generateEmailText(UserBookLoan loan) {
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Prezado(a), %s, matricula %d\n", loan.getUser().getName(), loan.getUser().getId()));
        writer.append(String.format("Informamos que o prazo de devolução do livro %s é amanhã (%s) \n", loan.getBook().getName(), GenerateBookReturnDate.getDate(loan.getLoan_date())));
        writer.append("Solicitamos que você renove o livro ou devolva, assim que possível.\n");
        writer.append("A Biblioteca Municipal está funcionando de segunda a sexta, das 9h às 17h.\n\n");
        writer.append("Atenciosamente,\n");
        writer.append("Setor de empréstimo e devolução\n");
        writer.append("BIBLIOTECA MUNICIPAL");
        return writer.toString();
    }
}
