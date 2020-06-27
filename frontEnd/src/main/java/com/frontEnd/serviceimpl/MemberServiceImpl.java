package com.frontEnd.serviceimpl;

import com.frontEnd.cache.CodeCache;
import com.frontEnd.cache.TokenCache;
import com.frontEnd.dao.MemberDao;
import com.frontEnd.entity.Member;
import com.frontEnd.service.MemberService;
import com.frontEnd.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;

    private final static Logger logger = LoggerFactory
            .getLogger(MemberService.class);

    @Override
    public boolean exists(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        return list != null && list.size() == 1;
    }

    @Override
    public boolean saveCode(Long phone, String code) {
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long phone, String content) {
        logger.info(phone + "|" + content);
        return true;
    }

    @Override
    public String getCode(Long phone) {
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {
        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.save(token, phone);
    }

    @Override
    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    @Override
    public Member getMemberByPhone(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean add(Member member){
        return memberDao.insert(member) > 0 ? true : false;
    }

    @Override
    public boolean update(Member member){
        return memberDao.update(member) > 0 ? true : false;
    }
}
