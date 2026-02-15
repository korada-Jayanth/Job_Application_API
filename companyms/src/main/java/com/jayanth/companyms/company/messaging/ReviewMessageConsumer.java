package com.jayanth.companyms.company.messaging;

import com.jayanth.companyms.company.companyService;
import com.jayanth.companyms.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReviewMessageConsumer {

    private final companyService companyService;

    public ReviewMessageConsumer(companyService companyService){
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void ConsumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
