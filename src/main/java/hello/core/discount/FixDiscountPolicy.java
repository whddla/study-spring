package hello.core.discount;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.springframework.stereotype.Component;

// 고정 할인 정책 구현체
@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
