package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.models.AbstractEntityController;
import ee.sda.mckirill.entities.PaymentType;
import ee.sda.mckirill.enums.PaymentTypeEnum;

import java.util.Optional;

public class PaymentTypeType extends AbstractEntityController {

    private static PaymentTypeType paymentTypeType;
    private PaymentType cashType;
    private PaymentType cardType;

    private PaymentTypeType() {
    }

    public static PaymentTypeType of() {
        if (paymentTypeType == null) {
            paymentTypeType = new PaymentTypeType();
        }
        return paymentTypeType;
    }

    public PaymentType getCash() {
        if (cashType == null) {
            cashType = getByType(PaymentTypeEnum.CASH);
        }
        return cashType;
    }

    public PaymentType getCard() {
        if (cardType == null) {
            cardType = getByType(PaymentTypeEnum.CARD);
        }
        return cardType;
    }

    public PaymentType getByType(PaymentTypeEnum paymentTypeEnum) {
        Optional<PaymentType> paymentType = session.byNaturalId(PaymentType.class).using("paymentName", paymentTypeEnum).loadOptional();
        if (paymentType.isEmpty()) {
            save(new PaymentType(paymentTypeEnum));
            paymentType = Optional.of(getByType(paymentTypeEnum));
        }
        return paymentType.get();
    }

    private void save(PaymentType paymentType) {
        session.beginTransaction();
        session.saveOrUpdate(paymentType);
        session.getTransaction().commit();
    }
}
