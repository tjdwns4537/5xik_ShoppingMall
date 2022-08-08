package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class WorkPeriod {

    @Getter @Setter
    LocalDateTime OrderDateTime; // 주문 들어온 시간
    @Getter @Setter
    LocalDateTime SendDateTIme; // 배송한 시간
    @Getter @Setter
    LocalDateTime ArriveDateTime; // 배송 도착 시간

    public WorkPeriod(LocalDateTime orderDateTime, LocalDateTime sendDateTIme, LocalDateTime arriveDateTime) {
        OrderDateTime = orderDateTime;
        SendDateTIme = sendDateTIme;
        ArriveDateTime = arriveDateTime;
    }

    public WorkPeriod() {

    }
}
