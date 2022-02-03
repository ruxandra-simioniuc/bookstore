package pos.proiect.bookstore.service.implementation;

import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.jwt.JwtBlacklist;
import pos.proiect.bookstore.repository.JwtBlacklistRepository;
import pos.proiect.bookstore.security.JwtTokenUtil;
import pos.proiect.bookstore.service.interfaces.JwtBlacklistService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    private JwtBlacklistRepository jwtBlacklistRepository;
    private JwtTokenUtil jwtTokenUtil;

    public JwtBlacklistServiceImpl(JwtBlacklistRepository jwtBlacklistRepository, JwtTokenUtil jwtTokenUtil) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean isJWTinBlacklist(String jwt) {
        return jwtBlacklistRepository.findById(jwt).isPresent();

    }

    @Override
    public boolean addJWTtoBlacklist(String jwt) {
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setJwt(jwt);
        try{
            jwtBlacklistRepository.save(jwtBlacklist);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<JwtBlacklist> getAllJWTs() {
        return jwtBlacklistRepository.findAll();
    }

    @Override
    public boolean isExpired(String jwt) {

        Date exp = jwtTokenUtil.getExpirationDateFromToken(jwt);

        return exp.before(new Date());

    }
}
