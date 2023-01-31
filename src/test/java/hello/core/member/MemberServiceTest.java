package hello.core.member;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 회원 도메인 - 회원 가입 테스트
public class MemberServiceTest {

//    DIP 의존관계를 지키지 않고있음.
//    MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

//    테스트를 돌리기전 실행
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void 회원가입(){
        // GIVEN
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
