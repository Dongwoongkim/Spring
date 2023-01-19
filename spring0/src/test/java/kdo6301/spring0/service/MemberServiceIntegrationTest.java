package kdo6301.spring0.service;

import kdo6301.spring0.domain.Member;
import kdo6301.spring0.repository.MemberRepository;
import kdo6301.spring0.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join()
    {
        //given
        Member member = new Member();
        member.setName("Kimawoong");
        //when
        Long saveId = memberService.join(member);

        //then
        Member One = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(One.getName());
    }

    @Test
    void 중복회원예외()
    {
        // given
        Member mem1 = new Member();
        mem1.setName("Spring1");

        Member mem2 = new Member();
        mem2.setName("Spring1");

        // when

        memberService.join(mem1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try
//        {
//            memberService.join(mem2);
//            fail();
//        }catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }

}