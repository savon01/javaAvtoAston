package lesson13;

public class PaymentIframeData {
    public String titlePrice;
    public String buttonPrice;
    public String phone;
    public String cardLabel;
    public String termCardLabel;
    public String cvcLabel;
    public String nameCardLabel;
    public boolean logosPresent;

    @Override
    public String toString() {
        return "PaymentIframeData{" +
                "titlePrice='" + titlePrice + '\'' +
                ", buttonPrice='" + buttonPrice + '\'' +
                ", phone='" + phone + '\'' +
                ", cardLabel='" + cardLabel + '\'' +
                ", termCardLabel='" + termCardLabel + '\'' +
                ", cvcLabel='" + cvcLabel + '\'' +
                ", nameCardLabel='" + nameCardLabel + '\'' +
                ", logosPresent=" + logosPresent +
                '}';
    }
}
