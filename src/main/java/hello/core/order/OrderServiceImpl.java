package hello.core.order;

import hello.core.Member.Member;
import hello.core.Member.MemberRepository;
import hello.core.discount.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 주문 서비스 구현체
@Component
@RequiredArgsConstructor // 생성자를 만들어줌
public class OrderServiceImpl implements OrderService {

//    DIP(단일책임원칙)을 위반 -> 인터페이스에만 의존하도록 변경해야한다.
//    OrderServiceImpl은 DiscountPolicy에만 의존해야하는데 현재 구체클래스에도 의존중(Fix,RateDiscountPolicy);
//    OrderServiceImpl이 객체를 직접 생성하고 구체클래스까지 직접 선택하는 것.
//    관심사를 분리해야 할 필요가 있다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    DIP(단일책임원칙)을 지킨 예 -> 구체클래스에 의존하지 않고 인터페이스에만 의존함
//    하지만 nullpointException 오류가 뜸.
//    private DiscountPolicy discountPolicy;

//    이로써 DIP를 지킴 인터페이스에만 의존하고있음
//    구현 객체는 AppConfig를 통해 생성자를 주입받음.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 의존관계 주입 방법 1
    // 생성자 주입
    // 요즘 쓰는 방식
    // 불변, 필수 의존관계에 사용
    // 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    // 만약 생성자가 1개만 있으면 @Autowired를 생략 가능
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 의존관계 주입 방법 2
    // 수성자 주입(setter 주입)
    // 선택, 변경 가능성이 있는 의존관계에 사용
    // 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법

//    @Autowired(required = false) // 주입할 대상이 없어도 동작하게 하려면 required = false
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    // 의존관계 주입 방법 3
    // 필드 주입 ,사용X
    // 외부 변경이 불가능하기 때문에 테스트하기 힘들다.
    // 간단하지만 결국 setter를 만들어서 생성해줘야함.
    // 그럴바엔 수정자 주입을 하는게 나음
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 의존관계 주입 방법 4
    // 일반 메서드 주입

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
