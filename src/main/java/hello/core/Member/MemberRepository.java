package hello.core.Member;

// 회원 저장소 interface
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
