package hello.core.discount;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.annotation.MainDiscountPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 정률 할인 정책 구현체
@Component
@Primary // dicountPolicy 에서 먼저 읽음
//@Qualifier("mainDiscountPolicy")
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
