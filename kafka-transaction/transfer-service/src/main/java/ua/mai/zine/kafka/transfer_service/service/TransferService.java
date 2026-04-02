package ua.mai.zine.kafka.transfer_service.service;

import ua.mai.zine.kafka.transfer_service.model.TransferRestModel;

public interface TransferService {
    public boolean transfer(TransferRestModel productPaymentRestModel);
}
