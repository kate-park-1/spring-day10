package Order.miniproject.Service;

import Order.miniproject.domain.Member;
import Order.miniproject.domain.dto.MemberDto;
import Order.miniproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long join(MemberDto memberDto){
    Member member = new Member();
    member.setName(memberDto.getName());
    member.setAddress(memberDto.getAddress());
    memberRepository.save(member);
    return member.getId();
  }

  public List<Member> findAllMembers(){
    return memberRepository.findAll();
  }

  public Member findOneMember(Long id){
    return memberRepository.findById(id);
  }
}
