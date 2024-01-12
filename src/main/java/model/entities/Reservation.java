package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    // Estatico para não ser criado um novo SDF para cada objeto da classe
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkin, Date checkout) {
        reservationDateValidation(checkin, checkout);
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }
    

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public long duration() {
        // transformando em milissegundos e pegando a diferença
        long diff = checkout.getTime() - checkin.getTime();
        // convertendo milissegundos em dias
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        /**
         * Caso o tipo das variáveis fosse localdate dava de usar apenas o checkin.until(checkout).getDays();
        */
    }


    public void updateDates(Date checkin, Date checkout) {
        reservationDateValidation(checkin, checkout);
        this.checkin = checkin;
        this.checkout = checkout;
    }

    private void reservationDateValidation(Date checkin, Date checkout) {
        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)) {
            throw new DomainException("Error in reservation: Reservation dates for update must be future dates");
        }
        if (!checkout.after(checkin)) {
            throw new DomainException("Error in reservation: Check-out date must be after check-in date");
        }
    }

    @Override
    public String toString() {
        return "Room " +roomNumber+
                ", check-in: "+sdf.format(checkin)+
                ", check-out: "+sdf.format(checkout)+
                ", "+duration()+" nights";
    }
}
