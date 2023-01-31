package hello.core.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 회원 서비스 구현체
@Component
public class MemberServiceImpl implements MemberService {

    // 구현 객체를 선택해 줘야함
//    MemberServiceImpl이 MemoryMemberRepository 와 MemberRepository같이 의존하고 있음
//    DIP를 위반하고있음.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    생성자로 인해 MemberRepository는 인터페이스에만 의존하고
//    구현 객체는 AppConfig를 통해 생성자를 주입받음.
    private final MemberRepository memberRepository;

//    생성자를 만듬
    @Autowired // 의존관계를 자동으로 주입해줌, AppConfig로 직접 넣었던 역할.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    
    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
